// IReservaRepositorio.java
package com.example.hotelapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.hotelapi.modelos.Reserva;

@Repository
public interface IReservaRepositorio extends JpaRepository<Reserva, Integer> {
}
