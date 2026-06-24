package com.paulcars.services;

import com.paulcars.models.Alquiler;
import com.paulcars.repositories.AlquilerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlquilerService {
    private final AlquilerRepository alquilerRepository;

    public List<Alquiler> getAllAlquileres() {
        return alquilerRepository.findAll();
    }

    public Optional<Alquiler> getAlquilerById(Integer id) {
        return alquilerRepository.findById(id);
    }

    public Alquiler saveAlquiler(Alquiler alquiler) {
        return alquilerRepository.save(alquiler);
    }

    public void deleteAlquiler(Integer id) {
        alquilerRepository.deleteById(id);
    }

    public List<Alquiler> getAlquileresPorCliente(Integer idCliente) {
        return alquilerRepository.findByClienteIdCliente(idCliente);
    }

    public List<Alquiler> getAlquileresPorEstado(Integer idEstado) {
        return alquilerRepository.findByEstadoAlquilerIdEstadoAlquiler(idEstado);
    }

    public Alquiler updateAlquiler(Integer id, Alquiler alquiler) {
        Optional<Alquiler> existing = alquilerRepository.findById(id);
        if (existing.isPresent()) {
            Alquiler a = existing.get();
            a.setCliente(alquiler.getCliente());
            a.setVehiculo(alquiler.getVehiculo());
            a.setLicenciaConducir(alquiler.getLicenciaConducir());
            a.setFechaReserva(alquiler.getFechaReserva());
            a.setFechaInicio(alquiler.getFechaInicio());
            a.setFechaFinEstimada(alquiler.getFechaFinEstimada());
            a.setFechaFinReal(alquiler.getFechaFinReal());
            a.setEstadoAlquiler(alquiler.getEstadoAlquiler());
            return alquilerRepository.save(a);
        }
        return null;
    }
}
