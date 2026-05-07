// IClienteRepositorio.java
package com.example.hotelapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.hotelapi.modelos.Cliente;

@Repository
public interface IClienteRepositorio extends JpaRepository<Cliente, Integer> {
}
