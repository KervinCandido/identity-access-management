package br.com.fiap.restaurant.iam.infra.mapper;

import br.com.fiap.restaurant.iam.core.domain.model.valueobject.OpeningHours;
import br.com.fiap.restaurant.iam.infra.persistence.entity.OpeningHoursEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OpeningHoursMapper {

    @Mapping(target = "restaurant", ignore = true)
    OpeningHoursEntity toEntity(OpeningHours domain);

    OpeningHours toDomain(OpeningHoursEntity entity);
}
