package com.example.hotelapi.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipos_habitacion")
public class TipoHabitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //nombre del tipo: sencilla, doble, suite
    private String nombre;

    //descripcion del tipo de habitacion
    private String descripcion;

    //precio base por noche en pesos
    private Double precioPorNoche;

    //numero maximo de personas
    private Integer capacidad;

    public TipoHabitacion() {
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Double getPrecioPorNoche() { return precioPorNoche; }
    public void setPrecioPorNoche(Double precioPorNoche) { this.precioPorNoche = precioPorNoche; }

    public Integer getCapacidad() { return capacidad; }
    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }

}
