package com.paulcars.controllers;

import com.paulcars.models.Vehiculo;
import com.paulcars.services.VehiculoService;
import com.paulcars.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/vehiculos")
@RequiredArgsConstructor
public class VehiculoController {
    private final VehiculoService vehiculoService;
    private final ModeloRepository modeloRepository;
    private final TiposVehiculoRepository tiposVehiculoRepository;
    private final CombustiblesRepository combustiblesRepository;
    private final EstadosVehiculoRepository estadosVehiculoRepository;

    @GetMapping
    public String listarVehiculos(Model model) {
        model.addAttribute("vehiculos", vehiculoService.getAllVehiculos());
        return "vehiculos/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("vehiculo", new Vehiculo());
        model.addAttribute("modelos", modeloRepository.findAll());
        model.addAttribute("tipos", tiposVehiculoRepository.findAll());
        model.addAttribute("combustibles", combustiblesRepository.findAll());
        model.addAttribute("estados", estadosVehiculoRepository.findAll());
        return "vehiculos/formulario";
    }

    @PostMapping("/guardar")
    public String guardarVehiculo(@ModelAttribute Vehiculo vehiculo) {
        vehiculoService.saveVehiculo(vehiculo);
        return "redirect:/vehiculos";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Integer id, Model model) {
        Optional<Vehiculo> vehiculo = vehiculoService.getVehiculoById(id);
        if (vehiculo.isPresent()) {
            model.addAttribute("vehiculo", vehiculo.get());
            model.addAttribute("modelos", modeloRepository.findAll());
            model.addAttribute("tipos", tiposVehiculoRepository.findAll());
            model.addAttribute("combustibles", combustiblesRepository.findAll());
            model.addAttribute("estados", estadosVehiculoRepository.findAll());
            return "vehiculos/formulario";
        }
        return "redirect:/vehiculos";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarVehiculo(@PathVariable Integer id, @ModelAttribute Vehiculo vehiculo) {
        vehiculoService.updateVehiculo(id, vehiculo);
        return "redirect:/vehiculos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarVehiculo(@PathVariable Integer id) {
        vehiculoService.deleteVehiculo(id);
        return "redirect:/vehiculos";
    }

    @GetMapping("/disponibles")
    public String vehiculosDisponibles(Model model) {
        model.addAttribute("vehiculos", vehiculoService.getVehiculosDisponibles());
        return "vehiculos/lista";
    }
}
