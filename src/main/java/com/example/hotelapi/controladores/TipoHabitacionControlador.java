// TipoHabitacionControlador.java
package com.example.hotelapi.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.hotelapi.modelos.TipoHabitacion;
import com.example.hotelapi.servicios.TipoHabitacionServicio;

@RestController
@RequestMapping("/hotelapi/v1/tipos-habitacion")
public class TipoHabitacionControlador {

    @Autowired
    private TipoHabitacionServicio servicio;

    @PostMapping
    public ResponseEntity<?> controladorGuardar(@RequestBody TipoHabitacion datos) {
        return ResponseEntity.status(HttpStatus.OK).body(servicio.guardar(datos));
    }

    @GetMapping
    public ResponseEntity<?> controladorListarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(servicio.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> controladorBuscarPorId(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> controladorModificar(@PathVariable Integer id, @RequestBody TipoHabitacion datos) {
        return ResponseEntity.status(HttpStatus.OK).body(servicio.modificar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> controladorEliminar(@PathVariable Integer id) {
        servicio.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Tipo de habitacion eliminado correctamente");
    }

}
