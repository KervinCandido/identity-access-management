package br.com.fiap.restaurant.iam.core.usecase.user;


import br.com.fiap.restaurant.iam.core.domain.model.User;
import br.com.fiap.restaurant.iam.core.domain.model.UserType;
import br.com.fiap.restaurant.iam.core.domain.model.util.AddressBuilder;
import br.com.fiap.restaurant.iam.core.domain.model.util.UserBuilder;
import br.com.fiap.restaurant.iam.core.domain.model.util.UserTypeBuilder;
import br.com.fiap.restaurant.iam.core.domain.roles.UserManagementRoles;
import br.com.fiap.restaurant.iam.core.exception.BusinessException;
import br.com.fiap.restaurant.iam.core.exception.OperationNotAllowedException;
import br.com.fiap.restaurant.iam.core.exception.ResourceNotFoundException;
import br.com.fiap.restaurant.iam.core.gateway.EventPublisher;
import br.com.fiap.restaurant.iam.core.gateway.LoggedUserGateway;
import br.com.fiap.restaurant.iam.core.gateway.UserGateway;
import br.com.fiap.restaurant.iam.core.gateway.UserTypeGateway;
import br.com.fiap.restaurant.iam.core.inbound.UpdateUserInput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para UpdateUserUseCase")
class UpdateUserUseCaseTest {

    @Mock
    private UserGateway userGateway;

    @Mock
    private UserTypeGateway userTypeGateway;

    @Mock
    private LoggedUserGateway loggedUserGateway;

    @Mock
    private EventPublisher<User> updateUserEventPublisher;

    @InjectMocks
    private UpdateUserUseCase updateUserUseCase;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Test
    @DisplayName("Deve atualizar nome, email, endereço e tipo de usuario com sucesso")
    void deveAtualizaNomeEmailEnderecoTipoDeUsuarioComSucesso() {
        // Arrange
        UUID userId = UUID.randomUUID();

        var currentAddress = new AddressBuilder()
                .withStreet("Rua Antiga")
                .withNumber("1")
                .withCity("Rio")
                .withState("RJ")
                .withZipCode("20000000")
                .withComplement("Casa")
                .build();

        UserType currentType = new UserTypeBuilder()
                .withId(1L)
                .withName("Cliente")
                .withRoleNames(java.util.Set.of("VIEW_MENU_ITEM"))
                .build();

        String oldusername = "oldusername";
        User currentUser = new UserBuilder()
                .withId(userId)
                .withName("Nome Antigo")
                .withEmail("old@teste.com")
                .withUsername("old.username")
                .withUsername(oldusername)
                .withAddress(currentAddress)
                .withUserType(currentType)
                .withPasswordHash("HASHED_OLD")
                .build();

        var newAddressInput = new AddressBuilder()
                .withStreet("Rua Nova")
                .withNumber("10")
                .withCity("Niterói")
                .withState("RJ")
                .withZipCode("24000000")
                .withComplement("Apto 101")
                .buildInput();

        Long newUserTypeId = 2L;
        UserType newUserType = new UserTypeBuilder()
                .withId(newUserTypeId)
                .withName("Dono")
                .withRoleNames(java.util.Set.of("RESTAURANT_OWNER"))
                .build();

        var input = new UpdateUserInput(
                userId,
                "  Maria Oliveira  ",
                "   maria.oliveira  ",
                "  maria@teste.com  ",
                newAddressInput,
                newUserTypeId
        );

        given(loggedUserGateway.hasRole(UserManagementRoles.UPDATE_USER)).willReturn(true);
        given(userGateway.findById(userId)).willReturn(Optional.of(currentUser));
        given(userGateway.existsUserWithEmail("maria@teste.com")).willReturn(false);
        given(userGateway.existsUserWithUserName("maria.oliveira")).willReturn(false);
        given(userTypeGateway.findById(newUserTypeId)).willReturn(Optional.of(newUserType));
        given(userGateway.save(any(User.class))).willAnswer(inv -> inv.getArgument(0));

        // Act
        updateUserUseCase.execute(input);

        // Assert
        then(userGateway).should().save(userCaptor.capture());
        User saved = userCaptor.getValue();

        assertThat(saved.getId()).isEqualTo(userId);
        assertThat(saved.getName()).isEqualTo("Maria Oliveira");
        assertThat(saved.getEmail()).isEqualTo("maria@teste.com");
        assertThat(saved.getPasswordHash()).isEqualTo("HASHED_OLD");

        assertThat(saved.getAddress()).isNotNull();
        assertThat(saved.getAddress().getStreet()).isEqualTo("Rua Nova");
        assertThat(saved.getAddress().getNumber()).isEqualTo("10");
        assertThat(saved.getAddress().getCity()).isEqualTo("Niterói");
        assertThat(saved.getAddress().getState()).isEqualTo("RJ");
        assertThat(saved.getAddress().getZipCode()).isEqualTo("24000000");
        assertThat(saved.getAddress().getComplement()).isEqualTo("Apto 101");

        assertThat(saved.getUserType()).isEqualTo(newUserType);

        then(loggedUserGateway).should().hasRole(UserManagementRoles.UPDATE_USER);
        then(userGateway).should().findById(userId);
        then(userGateway).should().existsUserWithEmail("maria@teste.com");
        then(userGateway).should().existsUserWithUserName("maria.oliveira");
        then(userTypeGateway).should().findById(newUserTypeId);
        then(updateUserEventPublisher).should().publish(userCaptor.getValue());
    }

    @Test
    @DisplayName("Não deve alterar nome/email/address/userType quando input vier com campos nulos")
    void naoDeveAlterarCamposQuandoEstiveremNulosNoInput() {
        // Arrange
        UUID userId = UUID.randomUUID();

        var currentAddress = new AddressBuilder().build();

        UserType currentType = new UserTypeBuilder()
                .withId(1L)
                .withName("Cliente")
                .withRoleNames(java.util.Set.of("VIEW_MENU_ITEM"))
                .build();

        User currentUser = new UserBuilder()
                .withId(userId)
                .withName("Nome Atual")
                .withEmail("atual@teste.com")
                .withAddress(currentAddress)
                .withUserType(currentType)
                .withPasswordHash("HASHED")
                .build();

        var input = new UpdateUserInput(
                userId,
                null,
                null,
                null,
                null,
                null
        );

        given(loggedUserGateway.hasRole(UserManagementRoles.UPDATE_USER)).willReturn(true);
        given(userGateway.findById(userId)).willReturn(Optional.of(currentUser));
        given(userGateway.save(any(User.class))).willAnswer(inv -> inv.getArgument(0));

        // Act
        updateUserUseCase.execute(input);

        // Assert
        then(userGateway).should().save(userCaptor.capture());
        User saved = userCaptor.getValue();

        assertThat(saved.getId()).isEqualTo(userId);
        assertThat(saved.getName()).isEqualTo("Nome Atual");
        assertThat(saved.getEmail()).isEqualTo("atual@teste.com");
        assertThat(saved.getAddress()).isEqualTo(currentAddress);
        assertThat(saved.getUserType()).isEqualTo(currentType);
        assertThat(saved.getPasswordHash()).isEqualTo("HASHED");

        // Não chama existsUserWithEmail porque email não mudou
        then(loggedUserGateway).should().hasRole(UserManagementRoles.UPDATE_USER);
        then(userGateway).should(never()).existsUserWithEmail(any());
        then(userGateway).should(never()).existsUserWithUserName(any());
        then(userTypeGateway).should(never()).findById(any());
        then(updateUserEventPublisher).should().publish(userCaptor.getValue());
    }

    @Test
    @DisplayName("Deve lançar NullPointerException quando o id do usuário for nulo")
    void deveLancarNullPointerQuandoIdDoUsuarioForNulo() {
        // Arrange
        var input = new UpdateUserInput(
                null,
                "Maria",
                "maria.oliveira",
                "maria@teste.com",
                null,
                null
        );

        given(loggedUserGateway.hasRole(UserManagementRoles.UPDATE_USER)).willReturn(true);

        // Act + Assert
        assertThatThrownBy(() -> updateUserUseCase.execute(input))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("User UUID cannot be null.");

        then(loggedUserGateway).should().hasRole(UserManagementRoles.UPDATE_USER);
        then(userGateway).shouldHaveNoInteractions();
        then(userTypeGateway).shouldHaveNoInteractions();
        then(updateUserEventPublisher).shouldHaveNoInteractions();
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException quando usuário não existir")
    void deveLancarResourceNotFoundQuandoUsuarioNaoExistir() {
        // Arrange
        UUID userId = UUID.randomUUID();
        var input = new UpdateUserInput(userId, "Maria", "maria.oliveira", "maria@teste.com", null, null);

        given(loggedUserGateway.hasRole(UserManagementRoles.UPDATE_USER)).willReturn(true);
        given(userGateway.findById(userId)).willReturn(Optional.empty());

        // Act + Assert
        assertThatThrownBy(() -> updateUserUseCase.execute(input))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("User with ID " + userId + " not found.");

        then(loggedUserGateway).should().hasRole(UserManagementRoles.UPDATE_USER);
        then(userGateway).should().findById(userId);
        then(userGateway).shouldHaveNoMoreInteractions();
        then(userTypeGateway).shouldHaveNoInteractions();
        then(updateUserEventPublisher).shouldHaveNoInteractions();
    }

    @Test
    @DisplayName("Deve lançar BusinessException quando novo endereço Email for vazio")
    void deveLancarBusinessExceptionQuandoNovoEnderecoEmailForVazio() {
        // Arrange
        UUID userId = UUID.randomUUID();

        User currentUser = new UserBuilder()
                .withId(userId)
                .withEmail("old@teste.com")
                .build();

        var input = new UpdateUserInput(userId, null, "maria.oliveira", "   ", null, null);

        given(loggedUserGateway.hasRole(UserManagementRoles.UPDATE_USER)).willReturn(true);
        given(userGateway.findById(userId)).willReturn(Optional.of(currentUser));

        // Act + Assert
        assertThatThrownBy(() -> updateUserUseCase.execute(input))
                .isInstanceOf(BusinessException.class)
                .hasMessage("Email cannot be blank.");

        then(loggedUserGateway).should().hasRole(UserManagementRoles.UPDATE_USER);
        then(userGateway).should().findById(userId);
        then(userGateway).shouldHaveNoMoreInteractions();
        then(userTypeGateway).shouldHaveNoInteractions();
        then(updateUserEventPublisher).shouldHaveNoInteractions();
    }

    @Test
    @DisplayName("Deve lançar BusinessException quando novo endeço email já estiver em uso")
    void deveLancarBusinessExceptionQuandoNovoEnderecoDeEmailJaEstiverEmUso() {
        // Arrange
        UUID userId = UUID.randomUUID();

        User currentUser = new UserBuilder()
                .withId(userId)
                .withEmail("old@teste.com")
                .build();

        String newEmailAddress = "new@teste.com";
        var input = new UpdateUserInput(userId, null, "maria.oliveira", newEmailAddress, null, null);

        given(loggedUserGateway.hasRole(UserManagementRoles.UPDATE_USER)).willReturn(true);
        given(userGateway.findById(userId)).willReturn(Optional.of(currentUser));
        given(userGateway.existsUserWithEmail(newEmailAddress)).willReturn(true);

        // Act + Assert
        assertThatThrownBy(() -> updateUserUseCase.execute(input))
                .isInstanceOf(BusinessException.class)
                .hasMessage("Email new@teste.com is already in use.");

        then(loggedUserGateway).should().hasRole(UserManagementRoles.UPDATE_USER);
        then(userGateway).should().findById(userId);
        then(userGateway).should().existsUserWithEmail(newEmailAddress);
        then(userGateway).shouldHaveNoMoreInteractions();
        then(userTypeGateway).shouldHaveNoInteractions();
        then(updateUserEventPublisher).shouldHaveNoInteractions();
    }

    @Test
    @DisplayName("Deve lançar BusinessException quando novo tipo de usuário não existir")
    void deveLancarBusinessExceptionQuandoNovoTipoDeUsuarioNaoExistir() {
        // Arrange
        UUID userId = UUID.randomUUID();

        UserType currentType = new UserTypeBuilder()
                .withId(1L)
                .withName("Cliente")
                .withRoleNames(java.util.Set.of("VIEW_MENU_ITEM"))
                .build();

        User currentUser = new UserBuilder()
                .withId(userId)
                .withUserType(currentType)
                .build();

        Long newUserTypeId = 999L;
        var input = new UpdateUserInput(userId, null, null, null, null, newUserTypeId);

        given(loggedUserGateway.hasRole(UserManagementRoles.UPDATE_USER)).willReturn(true);
        given(userGateway.findById(userId)).willReturn(Optional.of(currentUser));
        given(userTypeGateway.findById(newUserTypeId)).willReturn(Optional.empty());

        // Act + Assert
        assertThatThrownBy(() -> updateUserUseCase.execute(input))
                .isInstanceOf(BusinessException.class)
                .hasMessage("User type with ID " + newUserTypeId + " not found.");

        then(loggedUserGateway).should().hasRole(UserManagementRoles.UPDATE_USER);
        then(userGateway).should().findById(userId);
        then(userGateway).shouldHaveNoMoreInteractions();
        then(userTypeGateway).should().findById(newUserTypeId);
        then(userGateway).shouldHaveNoMoreInteractions();
        then(updateUserEventPublisher).shouldHaveNoInteractions();
    }

    @Test
    @DisplayName("Deve lançar OperationNotAllowedException quando usuário logado não tiver permissão")
    void deveLancarOperationNotAllowedQuandoUsuarioLogadoNaoTiverPermissao() {
        // Arrange
        var input = new UpdateUserInput(UUID.randomUUID(), "Maria", "maria.oliveira","maria@teste.com", null, null);
        given(loggedUserGateway.hasRole(UserManagementRoles.UPDATE_USER)).willReturn(false);

        // Act + Assert
        assertThatThrownBy(() -> updateUserUseCase.execute(input))
                .isInstanceOf(OperationNotAllowedException.class);

        then(loggedUserGateway).should().hasRole(UserManagementRoles.UPDATE_USER);
        then(userGateway).shouldHaveNoInteractions();
        then(userTypeGateway).shouldHaveNoInteractions();
        then(updateUserEventPublisher).shouldHaveNoInteractions();
    }

    @Test
    @DisplayName("Construtor deve lançar NullPointerException se receber nulo em algum campo")
    void construtorDeveLancarNullPointerSeReceberNuloEmAlgumCampo() {
        // Arrange

        // Act + Assert
        assertThatThrownBy(() -> new UpdateUserUseCase(null, userTypeGateway, loggedUserGateway, updateUserEventPublisher))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("userGateway cannot be null");
        assertThatThrownBy(() -> new UpdateUserUseCase(userGateway, null, loggedUserGateway, updateUserEventPublisher))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("userTypeGateway cannot be null");
        assertThatThrownBy(() -> new UpdateUserUseCase(userGateway, userTypeGateway, null, updateUserEventPublisher))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("loggedUserGateway cannot be null");
        assertThatThrownBy(() -> new UpdateUserUseCase(userGateway, userTypeGateway, loggedUserGateway, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("updateUserEventPublisher cannot be null");
    }

    @Test
    @DisplayName("Não deve buscar UserType quando input.userTypeId for igual ao userType atual")
    void naoDeveBuscarTipoDeUsuarioQuandoInputUserTypeIdForIgualAoAtual() {
        // Arrange
        UUID userId = UUID.randomUUID();

        Long currentUserTypeId = 1L;
        UserType currentType = new UserTypeBuilder()
                .withId(currentUserTypeId)
                .withName("Cliente")
                .withRoleNames(Set.of("VIEW_MENU_ITEM"))
                .build();

        User currentUser = new UserBuilder()
                .withId(userId)
                .withUserType(currentType)
                .withEmail("atual@teste.com")
                .withUsername("maria.oliveira")
                .build();

        var input = new UpdateUserInput(
                userId,
                "Novo Nome",
                "maria.oliveira",
                "atual@teste.com", // mantém email igual para não cair no existsUserWithEmail
                null,
                currentUserTypeId // <- igual ao atual (cobre o ramo que faltava)
        );

        given(loggedUserGateway.hasRole(UserManagementRoles.UPDATE_USER)).willReturn(true);
        given(userGateway.findById(userId)).willReturn(Optional.of(currentUser));
        given(userGateway.save(any(User.class))).willAnswer(inv -> inv.getArgument(0));

        // Act
        updateUserUseCase.execute(input);

        // Assert
        then(userGateway).should().save(userCaptor.capture());
        User saved = userCaptor.getValue();

        assertThat(saved.getUserType()).isEqualTo(currentType);
        assertThat(saved.getName()).isEqualTo("Novo Nome");
        assertThat(saved.getEmail()).isEqualTo("atual@teste.com");

        then(loggedUserGateway).should().hasRole(UserManagementRoles.UPDATE_USER);
        then(userGateway).should().findById(userId);
        then(userGateway).should(never()).existsUserWithEmail(anyString());
        then(userGateway).should(never()).existsUserWithUserName(anyString());
        then(userTypeGateway).shouldHaveNoInteractions();
        then(userGateway).should().save(any(User.class));
        then(updateUserEventPublisher).should().publish(userCaptor.getValue());
    }

    @Test
    @DisplayName("Deve lançar nullpointer quando o input for nulo")
    void deveLancaNullPointerQuandoInputForNulo() {
        assertThatThrownBy(() -> updateUserUseCase.execute(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("UpdateUserInput cannot be null");

        then(loggedUserGateway).shouldHaveNoInteractions();
        then(userGateway).shouldHaveNoInteractions();
        then(userTypeGateway).shouldHaveNoInteractions();
        then(updateUserEventPublisher).shouldHaveNoInteractions();
    }
}

