package com.sigaweb.entrenador.repository;

import com.sigaweb.entrenador.entities.Competencias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetenciasRepository extends JpaRepository<Competencias, Integer> {
    Competencias findByNombre(String nombre);
    List<Competencias> findAllByEstadoEquals(Short estado);
}
