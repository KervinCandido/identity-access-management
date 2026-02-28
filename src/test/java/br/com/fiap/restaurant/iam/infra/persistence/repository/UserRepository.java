package br.com.fiap.restaurant.iam.infra.persistence.repository;

import br.com.fiap.restaurant.iam.infra.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {}
