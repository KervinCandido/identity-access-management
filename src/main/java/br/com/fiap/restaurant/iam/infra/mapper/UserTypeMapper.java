package br.com.fiap.restaurant.iam.infra.mapper;

import br.com.fiap.restaurant.iam.core.domain.model.UserType;
import br.com.fiap.restaurant.iam.infra.persistence.entity.UserTypeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserTypeMapper {
    UserTypeEntity toEntity(UserType domain);
    UserType toDomain(UserTypeEntity entity);
}
