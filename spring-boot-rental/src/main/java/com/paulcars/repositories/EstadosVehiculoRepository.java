package com.paulcars.repositories;

import com.paulcars.models.EstadosVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadosVehiculoRepository extends JpaRepository<EstadosVehiculo, Integer> {
}
