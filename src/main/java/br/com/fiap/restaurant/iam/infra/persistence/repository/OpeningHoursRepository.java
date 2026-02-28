package br.com.fiap.restaurant.iam.infra.persistence.repository;

import br.com.fiap.restaurant.iam.infra.persistence.entity.OpeningHoursEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpeningHoursRepository extends JpaRepository<OpeningHoursEntity, Long> {
}
