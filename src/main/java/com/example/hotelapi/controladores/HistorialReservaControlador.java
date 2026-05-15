package com.example.hotelapi.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.hotelapi.servicios.HistorialReservaServicio;

@RestController
@RequestMapping("/hotelapi/v1/historial")
public class HistorialReservaControlador {

    @Autowired
    private HistorialReservaServicio servicio;

    @GetMapping
    public ResponseEntity<?> controladorListarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(servicio.listarTodos());
    }

    @GetMapping("/{codigoReserva}")
    public ResponseEntity<?> controladorBuscarPorCodigo(@PathVariable String codigoReserva) {
        return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarPorCodigo(codigoReserva));
    }

}
