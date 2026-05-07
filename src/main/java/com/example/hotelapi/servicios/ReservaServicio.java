// ReservaServicio.java
package com.example.hotelapi.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.hotelapi.modelos.Reserva;
import com.example.hotelapi.repositorios.IReservaRepositorio;

@Service
public class ReservaServicio {

    @Autowired
    private IReservaRepositorio repositorio;

    public Reserva guardar(Reserva datos) {
        if (datos.getFechaEntrada() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha de entrada es obligatoria");
        }
        if (datos.getFechaSalida() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha de salida es obligatoria");
        }
        if (datos.getFechaSalida().isBefore(datos.getFechaEntrada())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha de salida no puede ser antes de la entrada");
        }
        if (datos.getClienteId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El id del cliente es obligatorio");
        }
        if (datos.getHabitacionId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El id de la habitacion es obligatorio");
        }
        if (datos.getEstado() == null || datos.getEstado().isBlank()) {
            datos.setEstado("pendiente");
        }
        datos.setEstado(datos.getEstado().trim().toLowerCase());
        return repositorio.save(datos);
    }

    public List<Reserva> listarTodas() {
        return repositorio.findAll();
    }

    public Reserva buscarPorId(Integer id) {
        Optional<Reserva> encontrada = repositorio.findById(id);
        if (encontrada.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe una reserva con ese id");
        }
        return encontrada.get();
    }

    public Reserva modificar(Integer id, Reserva datos) {
        Reserva actual = buscarPorId(id);
        if (datos.getFechaEntrada() != null) {
            actual.setFechaEntrada(datos.getFechaEntrada());
        }
        if (datos.getFechaSalida() != null) {
            actual.setFechaSalida(datos.getFechaSalida());
        }
        if (datos.getPrecioTotal() != null) {
            actual.setPrecioTotal(datos.getPrecioTotal());
        }
        if (datos.getEstado() != null && !datos.getEstado().isBlank()) {
            actual.setEstado(datos.getEstado().trim().toLowerCase());
        }
        if (datos.getClienteId() != null) {
            actual.setClienteId(datos.getClienteId());
        }
        if (datos.getHabitacionId() != null) {
            actual.setHabitacionId(datos.getHabitacionId());
        }
        return repositorio.save(actual);
    }

    public void eliminar(Integer id) {
        Reserva actual = buscarPorId(id);
        repositorio.delete(actual);
    }

}
