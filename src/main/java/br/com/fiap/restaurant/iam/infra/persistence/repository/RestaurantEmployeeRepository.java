package br.com.fiap.restaurant.iam.infra.persistence.repository;

import br.com.fiap.restaurant.iam.infra.persistence.entity.RestaurantEmployeeEntity;
import br.com.fiap.restaurant.iam.infra.persistence.entity.RestaurantEmployeeEntity.RestaurantEmployeeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantEmployeeRepository extends JpaRepository<RestaurantEmployeeEntity, RestaurantEmployeeId> {
}
