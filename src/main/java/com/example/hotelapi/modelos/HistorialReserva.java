package com.example.hotelapi.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "historial_reservas")
public class HistorialReserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //codigo de la reserva afectada
    private String codigoReserva;

    //accion realizada: CREADA, ACTUALIZADA, CANCELADA, ELIMINADA
    private String accion;

    //fecha y hora exacta de la accion
    private LocalDateTime fechaAccion;

    //descripcion del cambio realizado
    private String descripcion;

    //id del usuario que hizo el cambio
    private Integer usuarioId;

}