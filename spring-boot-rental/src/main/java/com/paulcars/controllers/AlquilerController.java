package com.paulcars.controllers;

import com.paulcars.models.Alquiler;
import com.paulcars.services.AlquilerService;
import com.paulcars.repositories.ClienteRepository;
import com.paulcars.repositories.VehiculoRepository;
import com.paulcars.repositories.EstadosAlquilerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/alquileres")
@RequiredArgsConstructor
public class AlquilerController {
    private final AlquilerService alquilerService;
    private final ClienteRepository clienteRepository;
    private final VehiculoRepository vehiculoRepository;
    private final EstadosAlquilerRepository estadosAlquilerRepository;

    @GetMapping
    public String listarAlquileres(Model model) {
        model.addAttribute("alquileres", alquilerService.getAllAlquileres());
        return "alquileres/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("alquiler", new Alquiler());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("vehiculos", vehiculoRepository.findAll());
        model.addAttribute("estados", estadosAlquilerRepository.findAll());
        return "alquileres/formulario";
    }

    @PostMapping("/guardar")
    public String guardarAlquiler(@ModelAttribute Alquiler alquiler) {
        alquilerService.saveAlquiler(alquiler);
        return "redirect:/alquileres";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Integer id, Model model) {
        Optional<Alquiler> alquiler = alquilerService.getAlquilerById(id);
        if (alquiler.isPresent()) {
            model.addAttribute("alquiler", alquiler.get());
            model.addAttribute("clientes", clienteRepository.findAll());
            model.addAttribute("vehiculos", vehiculoRepository.findAll());
            model.addAttribute("estados", estadosAlquilerRepository.findAll());
            return "alquileres/formulario";
        }
        return "redirect:/alquileres";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarAlquiler(@PathVariable Integer id, @ModelAttribute Alquiler alquiler) {
        alquilerService.updateAlquiler(id, alquiler);
        return "redirect:/alquileres";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAlquiler(@PathVariable Integer id) {
        alquilerService.deleteAlquiler(id);
        return "redirect:/alquileres";
    }

    @GetMapping("/activos")
    public String alquileresActivos(Model model) {
        model.addAttribute("alquileres", alquilerService.getAlquileresPorEstado(1));
        return "alquileres/lista";
    }
}
