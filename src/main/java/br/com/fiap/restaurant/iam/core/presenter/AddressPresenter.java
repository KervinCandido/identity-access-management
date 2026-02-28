package br.com.fiap.restaurant.iam.core.presenter;

import br.com.fiap.restaurant.iam.core.domain.model.valueobject.Address;
import br.com.fiap.restaurant.iam.core.outbound.AddressOutput;

public class AddressPresenter {
    private AddressPresenter() {}


    public static AddressOutput toOutput(Address address) {
        return new AddressOutput(address.getStreet(), address.getNumber(), address.getCity(), address.getState(), address.getZipCode(), address.getComplement());
    }
}
