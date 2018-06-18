package com.sigaweb.entrenador.repository;

import com.sigaweb.entrenador.entities.Grados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradoRepository extends JpaRepository<Grados, Integer> {
        Grados findByNumero(String numero);
}
