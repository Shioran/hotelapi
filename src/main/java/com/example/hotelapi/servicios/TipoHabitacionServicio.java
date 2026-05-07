// TipoHabitacionServicio.java
package com.example.hotelapi.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.hotelapi.modelos.TipoHabitacion;
import com.example.hotelapi.repositorios.ITipoHabitacionRepositorio;

@Service
public class TipoHabitacionServicio {

    @Autowired
    private ITipoHabitacionRepositorio repositorio;

    public TipoHabitacion guardar(TipoHabitacion datos) {
        if (datos.getNombre() == null || datos.getNombre().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre del tipo es obligatorio");
        }
        if (datos.getPrecioPorNoche() == null || datos.getPrecioPorNoche() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El precio por noche debe ser mayor a 0");
        }
        if (datos.getCapacidad() == null || datos.getCapacidad() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La capacidad debe ser al menos 1");
        }
        datos.setNombre(datos.getNombre().trim().toLowerCase());
        return repositorio.save(datos);
    }

    public List<TipoHabitacion> listarTodos() {
        return repositorio.findAll();
    }

    public TipoHabitacion buscarPorId(Integer id) {
        Optional<TipoHabitacion> encontrado = repositorio.findById(id);
        if (encontrado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe un tipo de habitacion con ese id");
        }
        return encontrado.get();
    }

    public TipoHabitacion modificar(Integer id, TipoHabitacion datos) {
        TipoHabitacion actual = buscarPorId(id);
        if (datos.getNombre() != null && !datos.getNombre().isBlank()) {
            actual.setNombre(datos.getNombre().trim().toLowerCase());
        }
        if (datos.getDescripcion() != null && !datos.getDescripcion().isBlank()) {
            actual.setDescripcion(datos.getDescripcion());
        }
        if (datos.getPrecioPorNoche() != null && datos.getPrecioPorNoche() >= 0) {
            actual.setPrecioPorNoche(datos.getPrecioPorNoche());
        }
        if (datos.getCapacidad() != null && datos.getCapacidad() >= 1) {
            actual.setCapacidad(datos.getCapacidad());
        }
        return repositorio.save(actual);
    }

    public void eliminar(Integer id) {
        TipoHabitacion actual = buscarPorId(id);
        repositorio.delete(actual);
    }

}