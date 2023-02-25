package com.dam.proyectospring.controladores;

import com.dam.proyectospring.modelos.Piloto;
import com.dam.proyectospring.modelos.Usuario;
import com.dam.proyectospring.servicios.PilotoServicio;
import com.dam.proyectospring.servicios.UsuarioServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WebController {
    @Autowired
    private PilotoServicio pilotoServicio;
    @Autowired
    private UsuarioServicioImpl usuarioServicio;

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

    //Usuario
    @RequestMapping(value = "/usuarios")
    public String indexUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioServicio.findAllUsuarios());
        return "indexUsuario";
    }

    @GetMapping(value = "/usuarios/nuevo")
    public String nuevoUsuario(Model model) {
        Usuario usuario= new Usuario();
        model.addAttribute("usuario", usuario);
        return "createUsuario";
    }

    @PostMapping(value = "/usuarios")
    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuarioServicio.createUsuario(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping(value = "/usuarios/{id}")
    public String editarUsuario(@PathVariable int id, Model model) {
        Usuario usuario = usuarioServicio.findUsuario(id);
        model.addAttribute("usuario", usuario);
        return "updateUsuario";
    }

    @PostMapping(value = "/usuarios/{id}")
    public String actualizarUsuario(@PathVariable int id, @ModelAttribute("usuario") Usuario usuario) {
        Usuario usuarioExistente = usuarioServicio.findUsuario(id);

        usuarioExistente.setId(id);
        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setContrasenya(usuario.getContrasenya());

        usuarioServicio.updateUsuario(usuarioExistente);
        return "redirect:/usuarios";
    }

    @RequestMapping(value = "/usuarios/delete/{id}")
    public String eliminarUsuario(@PathVariable int id, Model model) {
        usuarioServicio.deleteUsuario(id);
        return "redirect:/usuarios";
    }
}
