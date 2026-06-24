package com.paulcars.repositories;

import com.paulcars.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Cliente findByNumeroDocumento(String numeroDocumento);
}
