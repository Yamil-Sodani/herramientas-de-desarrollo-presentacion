package com.paulcars.repositories;

import com.paulcars.models.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Integer> {
    List<Alquiler> findByEstadoAlquilerIdEstadoAlquiler(Integer idEstado);
    List<Alquiler> findByClienteIdCliente(Integer idCliente);
}
