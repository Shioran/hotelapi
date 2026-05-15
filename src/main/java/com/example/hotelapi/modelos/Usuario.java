package com.example.hotelapi.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //datos personales del huesped
    private String nombre;

    private String apellido;

    //documento de identidad: cedula
    private String documento;

    private String email;

    private String telefono;

    //datos de acceso al sistema
    @Column(unique = true)
    private String username;

    private String password;

    //rol: ADMIN o CLIENTE
    private String rol;

    //estado: activo o inactivo
    private String estado;

}