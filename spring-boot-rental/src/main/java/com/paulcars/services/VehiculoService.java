package com.paulcars.services;

import com.paulcars.models.Vehiculo;
import com.paulcars.repositories.VehiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehiculoService {
    private final VehiculoRepository vehiculoRepository;

    public List<Vehiculo> getAllVehiculos() {
        return vehiculoRepository.findAll();
    }

    public Optional<Vehiculo> getVehiculoById(Integer id) {
        return vehiculoRepository.findById(id);
    }

    public Vehiculo saveVehiculo(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    public void deleteVehiculo(Integer id) {
        vehiculoRepository.deleteById(id);
    }

    public List<Vehiculo> getVehiculosDisponibles() {
        return vehiculoRepository.findByEstadoIdEstado(1);
    }

    public Vehiculo updateVehiculo(Integer id, Vehiculo vehiculo) {
        Optional<Vehiculo> existing = vehiculoRepository.findById(id);
        if (existing.isPresent()) {
            Vehiculo v = existing.get();
            v.setPlaca(vehiculo.getPlaca());
            v.setColor(vehiculo.getColor());
            v.setAnio(vehiculo.getAnio());
            v.setNumeroMotor(vehiculo.getNumeroMotor());
            v.setNumeroVin(vehiculo.getNumeroVin());
            v.setPrecioDia(vehiculo.getPrecioDia());
            v.setPrecioHora(vehiculo.getPrecioHora());
            v.setModelo(vehiculo.getModelo());
            v.setTipo(vehiculo.getTipo());
            v.setCombustible(vehiculo.getCombustible());
            v.setEstado(vehiculo.getEstado());
            return vehiculoRepository.save(v);
        }
        return null;
    }
}
