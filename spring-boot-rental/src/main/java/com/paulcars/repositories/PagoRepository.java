package com.paulcars.repositories;

import com.paulcars.models.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {
    List<Pago> findByAlquilerIdAlquiler(Integer idAlquiler);
    
    @Query("SELECT SUM(p.montoTotal) FROM Pago p WHERE p.estadoPago = 'Pagado'")
    BigDecimal sumTotalPagos();
}
