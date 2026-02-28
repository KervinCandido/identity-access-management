package br.com.fiap.restaurant.iam.infra.persistence.repository;

import br.com.fiap.restaurant.iam.infra.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByName(String name);
    Optional<UserEntity> findByUsername(String username);
}
