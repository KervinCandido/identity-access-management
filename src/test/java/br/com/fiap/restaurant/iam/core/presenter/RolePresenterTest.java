package br.com.fiap.restaurant.iam.core.presenter;

import br.com.fiap.restaurant.iam.core.domain.model.Role;
import br.com.fiap.restaurant.iam.core.outbound.RoleOutput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Testes para RolePresenter")
class RolePresenterTest {

    @Test
    @DisplayName("Deve converter Role para RoleOutput corretamente")
    void shouldConvertRoleToRoleOutput() {
        Long id = 1L;
        String name = "ADMIN";
        
        Role role = new Role(id, name);

        RoleOutput output = RolePresenter.toOutput(role);

        assertThat(output).isNotNull();
        assertThat(output.id()).isEqualTo(id);
        assertThat(output.name()).isEqualTo(name);
    }
}
