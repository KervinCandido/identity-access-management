package br.com.fiap.restaurant.iam.infra.controller.mapper;

import br.com.fiap.restaurant.iam.core.inbound.OpeningHoursInput;
import br.com.fiap.restaurant.iam.infra.controller.request.OpeningHoursRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OpeningHoursRestMapper {
    OpeningHoursInput toInput(OpeningHoursRequest request);
}
