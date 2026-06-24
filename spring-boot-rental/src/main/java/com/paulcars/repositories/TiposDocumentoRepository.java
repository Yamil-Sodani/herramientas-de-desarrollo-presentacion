package com.paulcars.repositories;

import com.paulcars.models.TiposDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiposDocumentoRepository extends JpaRepository<TiposDocumento, Integer> {
}
