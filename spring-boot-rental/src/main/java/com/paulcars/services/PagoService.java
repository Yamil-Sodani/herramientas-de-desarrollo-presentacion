package com.paulcars.services;

import com.paulcars.models.Pago;
import com.paulcars.repositories.PagoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PagoService {
    private final PagoRepository pagoRepository;

    public List<Pago> getAllPagos() {
        return pagoRepository.findAll();
    }

    public Optional<Pago> getPagoById(Integer id) {
        return pagoRepository.findById(id);
    }

    public Pago savePago(Pago pago) {
        return pagoRepository.save(pago);
    }

    public void deletePago(Integer id) {
        pagoRepository.deleteById(id);
    }

    public List<Pago> getPagosPorAlquiler(Integer idAlquiler) {
        return pagoRepository.findByAlquilerIdAlquiler(idAlquiler);
    }

    public BigDecimal getTotalPagos() {
        BigDecimal total = pagoRepository.sumTotalPagos();
        return total != null ? total : BigDecimal.ZERO;
    }

    public Pago updatePago(Integer id, Pago pago) {
        Optional<Pago> existing = pagoRepository.findById(id);
        if (existing.isPresent()) {
            Pago p = existing.get();
            p.setAlquiler(pago.getAlquiler());
            p.setFechaPago(pago.getFechaPago());
            p.setMontoTotal(pago.getMontoTotal());
            p.setMetodoPago(pago.getMetodoPago());
            p.setEstadoPago(pago.getEstadoPago());
            p.setComentarios(pago.getComentarios());
            return pagoRepository.save(p);
        }
        return null;
    }
}
