// ClienteServicio.java
package com.example.hotelapi.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.hotelapi.modelos.Cliente;
import com.example.hotelapi.repositorios.IClienteRepositorio;

@Service
public class ClienteServicio {

    @Autowired
    private IClienteRepositorio repositorio;

    public Cliente guardar(Cliente datos) {
        if (datos.getNombre() == null || datos.getNombre().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre del cliente es obligatorio");
        }
        if (datos.getApellido() == null || datos.getApellido().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El apellido del cliente es obligatorio");
        }
        if (datos.getDocumento() == null || datos.getDocumento().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El documento del cliente es obligatorio");
        }
        datos.setNombre(datos.getNombre().trim());
        datos.setApellido(datos.getApellido().trim());
        datos.setDocumento(datos.getDocumento().trim());
        return repositorio.save(datos);
    }

    public List<Cliente> listarTodos() {
        return repositorio.findAll();
    }

    public Cliente buscarPorId(Integer id) {
        Optional<Cliente> encontrado = repositorio.findById(id);
        if (encontrado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe un cliente con ese id");
        }
        return encontrado.get();
    }

    public Cliente modificar(Integer id, Cliente datos) {
        Cliente actual = buscarPorId(id);
        if (datos.getNombre() != null && !datos.getNombre().isBlank()) {
            actual.setNombre(datos.getNombre().trim());
        }
        if (datos.getApellido() != null && !datos.getApellido().isBlank()) {
            actual.setApellido(datos.getApellido().trim());
        }
        if (datos.getDocumento() != null && !datos.getDocumento().isBlank()) {
            actual.setDocumento(datos.getDocumento().trim());
        }
        if (datos.getEmail() != null && !datos.getEmail().isBlank()) {
            actual.setEmail(datos.getEmail().trim());
        }
        if (datos.getTelefono() != null && !datos.getTelefono().isBlank()) {
            actual.setTelefono(datos.getTelefono().trim());
        }
        return repositorio.save(actual);
    }

    public void eliminar(Integer id) {
        Cliente actual = buscarPorId(id);
        repositorio.delete(actual);
    }

}
