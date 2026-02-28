package br.com.fiap.restaurant.iam.infra.mapper;

import br.com.fiap.restaurant.iam.core.domain.model.valueobject.Address;
import br.com.fiap.restaurant.iam.infra.persistence.entity.AddressEmbeddableEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressEmbeddableEntity toEntity(Address domain);
    Address toDomain(AddressEmbeddableEntity entity);
}
