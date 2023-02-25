package com.dam.proyectospring.controladores;

import com.dam.proyectospring.modelos.Piloto;
import com.dam.proyectospring.servicios.PilotoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WebController {
    @Autowired
    private PilotoServicio pilotoServicio;

    //Pilotos
    @RequestMapping(value = {"/", "/pilotos"})
    public String index(Model model) {
        model.addAttribute("pilotos", pilotoServicio.findAllPilotos());
        return "index";
    }

    @GetMapping(value = "/pilotos/nuevo")
    public String nuevoPiloto(Model model) {
        Piloto piloto = new Piloto();
        model.addAttribute("piloto", piloto);
        return "createPiloto";
    }

    @PostMapping(value = "/pilotos")
    public String guardarPiloto(@ModelAttribute("piloto") Piloto piloto) {
        pilotoServicio.createPiloto(piloto);
        return "redirect:/pilotos";
    }

    @GetMapping(value = "/pilotos/{id}")
    public String editarPiloto(@PathVariable String id, Model model) {
        Piloto piloto = pilotoServicio.findPiloto(id);
        model.addAttribute("piloto", piloto);
        return "updatePiloto";
    }

    @PostMapping(value = "/pilotos/{id}")
    public String actualizarPiloto(@PathVariable String id, @ModelAttribute("piloto") Piloto piloto) {
        Piloto piloExistente = pilotoServicio.findPiloto(id);

        piloExistente.setId(id);
        piloExistente.setNombre(piloto.getNombre());
        piloExistente.setEquipo(piloto.getEquipo());
        piloExistente.setPais(piloto.getPais());
        piloExistente.setFechaNacimiento(piloto.getFechaNacimiento());

        pilotoServicio.updatePiloto(piloExistente);
        return "redirect:/pilotos";
    }

    @RequestMapping(value = "/pilotos/delete/{id}")
    public String eliminarPiloto(@PathVariable String id, Model model) {
        pilotoServicio.deletePiloto(id);
        return "redirect:/pilotos";
    }
}
