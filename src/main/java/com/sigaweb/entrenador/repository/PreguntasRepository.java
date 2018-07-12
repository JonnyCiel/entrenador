package com.sigaweb.entrenador.repository;

import com.sigaweb.entrenador.entities.CompetenciaComponente;
import com.sigaweb.entrenador.entities.Preguntas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreguntasRepository extends JpaRepository<Preguntas, Integer> {
    List<Preguntas> findAllByEstadoEquals(Short estado);
}
