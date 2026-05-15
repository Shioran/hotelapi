package com.example.hotelapi.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.hotelapi.modelos.Usuario;
import com.example.hotelapi.repositorios.IUsuarioRepositorio;

@Service
public class UsuarioServicio {

    @Autowired
    private IUsuarioRepositorio repositorio;

    public Usuario guardar(Usuario datos) {
        if (datos.getNombre() == null || datos.getNombre().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre es obligatorio");
        }
        if (datos.getUsername() == null || datos.getUsername().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El username es obligatorio");
        }
        if (datos.getPassword() == null || datos.getPassword().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La contrasena es obligatoria");
        }
        if (datos.getDocumento() == null || datos.getDocumento().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El documento es obligatorio");
        }
        if (datos.getRol() == null || datos.getRol().isBlank()) {
            datos.setRol("CLIENTE");
        }
        datos.setEstado("activo");
        datos.setNombre(datos.getNombre().trim());
        datos.setApellido(datos.getApellido() != null ? datos.getApellido().trim() : "");
        datos.setUsername(datos.getUsername().trim().toLowerCase());
        datos.setRol(datos.getRol().trim().toUpperCase());
        return repositorio.save(datos);
    }

    public List<Usuario> listarTodos() {
        return repositorio.findAll();
    }

    public Usuario buscarPorId(Integer id) {
        Optional<Usuario> encontrado = repositorio.findById(id);
        if (encontrado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe un usuario con ese id");
        }
        return encontrado.get();
    }

    public Usuario modificar(Integer id, Usuario datos) {
        Usuario actual = buscarPorId(id);
        if (datos.getNombre() != null && !datos.getNombre().isBlank()) {
            actual.setNombre(datos.getNombre().trim());
        }
        if (datos.getPassword() != null && !datos.getPassword().isBlank()) {
            actual.setPassword(datos.getPassword());
        }
        if (datos.getRol() != null && !datos.getRol().isBlank()) {
            actual.setRol(datos.getRol().trim().toUpperCase());
        }
        if (datos.getEstado() != null && !datos.getEstado().isBlank()) {
            actual.setEstado(datos.getEstado().trim().toLowerCase());
        }
        return repositorio.save(actual);
    }

    public void eliminar(Integer id) {
        Usuario actual = buscarPorId(id);
        repositorio.delete(actual);
    }

    //login: valida username y password, devuelve el usuario si es correcto
    public Usuario login(String username, String password) {
        Optional<Usuario> encontrado = repositorio.findByUsername(username.trim().toLowerCase());
        if (encontrado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario no encontrado");
        }
        Usuario usuario = encontrado.get();
        if (!usuario.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Contrasena incorrecta");
        }
        if (!usuario.getEstado().equals("activo")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario inactivo");
        }
        return usuario;
    }


}
