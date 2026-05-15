package com.example.hotelapi.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.hotelapi.modelos.Usuario;
import com.example.hotelapi.servicios.UsuarioServicio;

import java.util.Map;

@RestController
@RequestMapping("/hotelapi/v1/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio servicio;

    @PostMapping
    public ResponseEntity<?> controladorGuardar(@RequestBody Usuario datos) {
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
    public ResponseEntity<?> controladorModificar(@PathVariable Integer id, @RequestBody Usuario datos) {
        return ResponseEntity.status(HttpStatus.OK).body(servicio.modificar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> controladorEliminar(@PathVariable Integer id) {
        servicio.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado correctamente");
    }

    //endpoint de login: recibe username y password, devuelve el usuario con su rol
    @PostMapping("/login")
    public ResponseEntity<?> controladorLogin(@RequestBody Map<String, String> credenciales) {
        String username = credenciales.get("username");
        String password = credenciales.get("password");
        return ResponseEntity.status(HttpStatus.OK).body(servicio.login(username, password));
    }

}
