package com.example.hotelapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.hotelapi.modelos.HistorialReserva;
import java.util.List;

@Repository
public interface IHistorialReservaRepositorio extends JpaRepository<HistorialReserva, Integer> {
    //buscar historial por codigo de reserva
    List<HistorialReserva> findByCodigoReserva(String codigoReserva);
}
