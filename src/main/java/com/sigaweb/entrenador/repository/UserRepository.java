package com.sigaweb.entrenador.repository;

import com.sigaweb.entrenador.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByCorreo(String correo);
}
