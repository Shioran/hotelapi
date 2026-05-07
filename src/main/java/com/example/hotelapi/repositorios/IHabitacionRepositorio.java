// IHabitacionRepositorio.java
package com.example.hotelapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.hotelapi.modelos.Habitacion;

@Repository
public interface IHabitacionRepositorio extends JpaRepository<Habitacion, Integer> {
}
