package com.example.hotelapi.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "habitaciones")
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //numero de habitacion: 101, 202, etc.
    private String numero;

    //piso donde se ubica
    private Integer piso;

    //estado actual: disponible, ocupada, mantenimiento
    private String estado;

    //id del tipo de habitacion al que pertenece
    private Integer tipoHabitacionId;

    public Habitacion() {
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public Integer getPiso() { return piso; }
    public void setPiso(Integer piso) { this.piso = piso; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Integer getTipoHabitacionId() { return tipoHabitacionId; }
    public void setTipoHabitacionId(Integer tipoHabitacionId) { this.tipoHabitacionId = tipoHabitacionId; }

}
