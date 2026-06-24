package com.paulcars.repositories;

import com.paulcars.models.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {
    List<Vehiculo> findByEstadoIdEstado(Integer idEstado);
    Vehiculo findByPlaca(String placa);
}
