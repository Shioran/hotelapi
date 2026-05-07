// HabitacionServicio.java
package com.example.hotelapi.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.hotelapi.modelos.Habitacion;
import com.example.hotelapi.repositorios.IHabitacionRepositorio;

@Service
public class HabitacionServicio {

    @Autowired
    private IHabitacionRepositorio repositorio;

    public Habitacion guardar(Habitacion datos) {
        if (datos.getNumero() == null || datos.getNumero().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El numero de habitacion es obligatorio");
        }
        if (datos.getPiso() == null || datos.getPiso() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El piso debe ser mayor a 0");
        }
        if (datos.getEstado() == null || datos.getEstado().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El estado de la habitacion es obligatorio");
        }
        if (datos.getTipoHabitacionId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El tipo de habitacion es obligatorio");
        }
        datos.setNumero(datos.getNumero().trim());
        datos.setEstado(datos.getEstado().trim().toLowerCase());
        return repositorio.save(datos);
    }

    public List<Habitacion> listarTodas() {
        return repositorio.findAll();
    }

    public Habitacion buscarPorId(Integer id) {
        Optional<Habitacion> encontrada = repositorio.findById(id);
        if (encontrada.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe una habitacion con ese id");
        }
        return encontrada.get();
    }

    public Habitacion modificar(Integer id, Habitacion datos) {
        Habitacion actual = buscarPorId(id);
        if (datos.getNumero() != null && !datos.getNumero().isBlank()) {
            actual.setNumero(datos.getNumero().trim());
        }
        if (datos.getPiso() != null && datos.getPiso() >= 1) {
            actual.setPiso(datos.getPiso());
        }
        if (datos.getEstado() != null && !datos.getEstado().isBlank()) {
            actual.setEstado(datos.getEstado().trim().toLowerCase());
        }
        if (datos.getTipoHabitacionId() != null) {
            actual.setTipoHabitacionId(datos.getTipoHabitacionId());
        }
        return repositorio.save(actual);
    }

    public void eliminar(Integer id) {
        Habitacion actual = buscarPorId(id);
        repositorio.delete(actual);
    }

}
