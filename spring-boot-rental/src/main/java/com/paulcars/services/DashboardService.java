package com.paulcars.services;

import com.paulcars.models.Alquiler;
import com.paulcars.models.Cliente;
import com.paulcars.models.Vehiculo;
import com.paulcars.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final VehiculoRepository vehiculoRepository;
    private final ClienteRepository clienteRepository;
    private final AlquilerRepository alquilerRepository;
    private final PagoRepository pagoRepository;
    private final EstadosVehiculoRepository estadosVehiculoRepository;
    private final EstadosAlquilerRepository estadosAlquilerRepository;

    public Map<String, Object> getDashboardData() {
        Map<String, Object> data = new HashMap<>();

        // Alquileres activos
        List<Alquiler> alquileresPendientes = alquilerRepository.findByEstadoAlquilerIdEstadoAlquiler(1);
        data.put("alquilesActivos", alquileresPendientes.size());
        data.put("alquilesCompletados", alquilerRepository.findByEstadoAlquilerIdEstadoAlquiler(2).size());

        // Vehículos disponibles
        List<Vehiculo> vehiculosDisponibles = vehiculoRepository.findByEstadoIdEstado(1);
        data.put("vehiculosDisponibles", vehiculosDisponibles.size());
        data.put("vehiculosTotal", vehiculoRepository.findAll().size());

        // Ingresos totales
        BigDecimal ingresosTotales = pagoRepository.sumTotalPagos();
        data.put("ingresosTotales", ingresosTotales != null ? ingresosTotales.toString() : "0.00");

        // Clientes activos
        List<Cliente> clientes = clienteRepository.findAll();
        data.put("clientesActivos", clientes.size());
        data.put("clientesRegistrados", clientes.size());

        // Distribución por tipo de vehículo
        List<Vehiculo> todosVehiculos = vehiculoRepository.findAll();
        Map<String, Integer> distribucion = new HashMap<>();
        todosVehiculos.forEach(v -> {
            String tipo = v.getTipo().getNombreTipoVehiculo();
            distribucion.put(tipo, distribucion.getOrDefault(tipo, 0) + 1);
        });
        data.put("distribucionPorTipo", distribucion);

        return data;
    }
}
