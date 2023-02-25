package com.dam.proyectospring.repositorios;

import com.dam.proyectospring.modelos.Usuario;
import org.springframework.data.repository.ListCrudRepository;

public interface UsuarioRepositorio extends ListCrudRepository<Usuario, Integer> {
    Usuario findByNombre(String nombre);
}
