package com.sigaweb.entrenador.repository;

import com.sigaweb.entrenador.entities.Competencias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenciasRepository extends JpaRepository<Competencias, Integer> {
    Competencias findByNombre(String nombre);
}
