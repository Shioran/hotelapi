package com.example.hotelapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.hotelapi.modelos.Usuario;
import java.util.Optional;

@Repository
public interface IUsuarioRepositorio extends JpaRepository<Usuario, Integer> {
    //buscar usuario por username para el login
    Optional<Usuario> findByUsername(String username);
}
