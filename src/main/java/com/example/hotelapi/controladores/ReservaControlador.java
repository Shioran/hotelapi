// ReservaControlador.java
package com.example.hotelapi.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.hotelapi.modelos.Reserva;
import com.example.hotelapi.servicios.ReservaServicio;

@RestController
@RequestMapping("/hotelapi/v1/reservas")
public class ReservaControlador {

    @Autowired
    private ReservaServicio servicio;

    @PostMapping
    public ResponseEntity<?> controladorGuardar(@RequestBody Reserva datos) {
        return ResponseEntity.status(HttpStatus.OK).body(servicio.guardar(datos));
    }

    @GetMapping
    public ResponseEntity<?> controladorListarTodas() {
        return ResponseEntity.status(HttpStatus.OK).body(servicio.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> controladorBuscarPorId(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> controladorModificar(@PathVariable Integer id, @RequestBody Reserva datos) {
        return ResponseEntity.status(HttpStatus.OK).body(servicio.modificar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> controladorEliminar(@PathVariable Integer id) {
        servicio.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Reserva eliminada correctamente");
    }

}