package com.paulcars.repositories;

import com.paulcars.models.TiposVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiposVehiculoRepository extends JpaRepository<TiposVehiculo, Integer> {
}
