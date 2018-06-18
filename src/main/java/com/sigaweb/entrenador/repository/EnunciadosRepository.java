package com.sigaweb.entrenador.repository;

import com.sigaweb.entrenador.entities.Enunciados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnunciadosRepository extends JpaRepository<Enunciados, Integer> {
}
