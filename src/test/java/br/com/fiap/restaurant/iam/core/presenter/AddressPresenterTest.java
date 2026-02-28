package br.com.fiap.restaurant.iam.core.presenter;

import br.com.fiap.restaurant.iam.core.domain.model.valueobject.Address;
import br.com.fiap.restaurant.iam.core.outbound.AddressOutput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Testes para AddressPresenter")
class AddressPresenterTest {

    @Test
    @DisplayName("Deve converter Address para AddressOutput corretamente")
    void shouldConvertAddressToAddressOutput() {
        String street = "Rua das Flores";
        String number = "123";
        String city = "São Paulo";
        String state = "SP";
        String zipCode = "01234-567";
        String complement = "Apto 101";

        Address address = new Address(street, number, city, state, zipCode, complement);

        AddressOutput output = AddressPresenter.toOutput(address);

        assertThat(output).isNotNull();
        assertThat(output.street()).isEqualTo(street);
        assertThat(output.number()).isEqualTo(number);
        assertThat(output.city()).isEqualTo(city);
        assertThat(output.state()).isEqualTo(state);
        assertThat(output.zipCode()).isEqualTo(zipCode);
        assertThat(output.complement()).isEqualTo(complement);
    }
}
