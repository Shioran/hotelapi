// ITipoHabitacionRepositorio.java
package com.example.hotelapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.hotelapi.modelos.TipoHabitacion;

@Repository
public interface ITipoHabitacionRepositorio extends JpaRepository<TipoHabitacion, Integer> {
}
