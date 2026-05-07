// HabitacionControlador.java
package com.example.hotelapi.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.hotelapi.modelos.Habitacion;
import com.example.hotelapi.servicios.HabitacionServicio;

@RestController
@RequestMapping("/hotelapi/v1/habitaciones")
public class HabitacionControlador {

    @Autowired
    private HabitacionServicio servicio;

    @PostMapping
    public ResponseEntity<?> controladorGuardar(@RequestBody Habitacion datos) {
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
    public ResponseEntity<?> controladorModificar(@PathVariable Integer id, @RequestBody Habitacion datos) {
        return ResponseEntity.status(HttpStatus.OK).body(servicio.modificar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> controladorEliminar(@PathVariable Integer id) {
        servicio.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Habitacion eliminada correctamente");
    }

}
