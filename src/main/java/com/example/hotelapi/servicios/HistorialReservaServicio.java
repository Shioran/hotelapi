package com.example.hotelapi.servicios;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotelapi.modelos.HistorialReserva;
import com.example.hotelapi.repositorios.IHistorialReservaRepositorio;

@Service
public class HistorialReservaServicio {

    @Autowired
    private IHistorialReservaRepositorio repositorio;

    //registra una accion en el historial automaticamente
    public void registrar(String codigoReserva, String accion, String descripcion, Integer usuarioId) {
        HistorialReserva registro = new HistorialReserva();
        registro.setCodigoReserva(codigoReserva);
        registro.setAccion(accion);
        registro.setDescripcion(descripcion);
        registro.setFechaAccion(LocalDateTime.now());
        registro.setUsuarioId(usuarioId);
        repositorio.save(registro);
    }

    public List<HistorialReserva> listarTodos() {
        return repositorio.findAll();
    }

    public List<HistorialReserva> buscarPorCodigo(String codigoReserva) {
        return repositorio.findByCodigoReserva(codigoReserva);
    }

}
