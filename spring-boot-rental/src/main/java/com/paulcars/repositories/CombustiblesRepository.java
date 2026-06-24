package com.paulcars.repositories;

import com.paulcars.models.Combustibles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CombustiblesRepository extends JpaRepository<Combustibles, Integer> {
}
