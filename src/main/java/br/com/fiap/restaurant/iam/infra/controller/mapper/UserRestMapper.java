package br.com.fiap.restaurant.iam.infra.controller.mapper;

import br.com.fiap.restaurant.iam.core.inbound.CreateUserInput;
import br.com.fiap.restaurant.iam.core.inbound.UpdateUserInput;
import br.com.fiap.restaurant.iam.core.outbound.UserOutput;
import br.com.fiap.restaurant.iam.core.outbound.UserSummaryOutput;
import br.com.fiap.restaurant.iam.infra.controller.request.UserRequest;
import br.com.fiap.restaurant.iam.infra.controller.response.UserResponse;
import br.com.fiap.restaurant.iam.infra.controller.response.UserSummaryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {AddressRestMapper.class})
public interface UserRestMapper {
    @Mapping(target = "id", source = "id")
    UserResponse toResponse(UserOutput output);

    UserSummaryResponse toResponse(UserSummaryOutput output);

    CreateUserInput toInput(UserRequest response);

    @Mapping(target = "id", source = "id")
    UpdateUserInput toUpdateInput(UserRequest response, UUID id);
}
