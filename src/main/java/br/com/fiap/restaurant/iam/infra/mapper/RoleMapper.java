package br.com.fiap.restaurant.iam.infra.mapper;

import br.com.fiap.restaurant.iam.core.domain.model.Role;
import br.com.fiap.restaurant.iam.infra.persistence.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleEntity toEntity(Role domain);
    Role toDomain(RoleEntity entity);
}
