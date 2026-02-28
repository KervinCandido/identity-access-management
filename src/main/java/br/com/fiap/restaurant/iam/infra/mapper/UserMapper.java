package br.com.fiap.restaurant.iam.infra.mapper;

import br.com.fiap.restaurant.iam.core.domain.model.User;
import br.com.fiap.restaurant.iam.infra.persistence.entity.UserEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {UserTypeMapper.class, AddressMapper.class})
public interface UserMapper {

    @Mapping(target = "passwordHash", source = "passwordHash")
    @Mapping(target = "authorities", ignore = true)
    UserEntity toEntity(User domain);

    @Mapping(target = "passwordHash", source = "passwordHash")
    User toDomain(UserEntity entity);
}
