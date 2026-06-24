package com.paulcars.controllers;

import com.paulcars.models.Pago;
import com.paulcars.services.PagoService;
import com.paulcars.repositories.AlquilerRepository;
import com.paulcars.repositories.MetodosPagoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/pagos")
@RequiredArgsConstructor
public class PagoController {
    private final PagoService pagoService;
    private final AlquilerRepository alquilerRepository;
    private final MetodosPagoRepository metodosPagoRepository;

    @GetMapping
    public String listarPagos(Model model) {
        model.addAttribute("pagos", pagoService.getAllPagos());
        model.addAttribute("totalIngresos", pagoService.getTotalPagos());
        return "pagos/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("pago", new Pago());
        model.addAttribute("alquileres", alquilerRepository.findAll());
        model.addAttribute("metodos", metodosPagoRepository.findAll());
        return "pagos/formulario";
    }

    @PostMapping("/guardar")
    public String guardarPago(@ModelAttribute Pago pago) {
        pagoService.savePago(pago);
        return "redirect:/pagos";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Integer id, Model model) {
        Optional<Pago> pago = pagoService.getPagoById(id);
        if (pago.isPresent()) {
            model.addAttribute("pago", pago.get());
            model.addAttribute("alquileres", alquilerRepository.findAll());
            model.addAttribute("metodos", metodosPagoRepository.findAll());
            return "pagos/formulario";
        }
        return "redirect:/pagos";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarPago(@PathVariable Integer id, @ModelAttribute Pago pago) {
        pagoService.updatePago(id, pago);
        return "redirect:/pagos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPago(@PathVariable Integer id) {
        pagoService.deletePago(id);
        return "redirect:/pagos";
    }
}
