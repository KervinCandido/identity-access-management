package br.com.fiap.restaurant.iam.infra.controller.mapper;

import br.com.fiap.restaurant.iam.core.inbound.AddressInput;
import br.com.fiap.restaurant.iam.infra.controller.request.AddressRequest;
import br.com.fiap.restaurant.iam.infra.controller.response.AddressResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressRestMapper {
    AddressInput toInput(AddressRequest addressRequest);
    AddressResponse toResponse(AddressInput addressInput);
}
