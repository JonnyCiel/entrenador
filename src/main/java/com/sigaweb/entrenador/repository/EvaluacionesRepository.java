package com.sigaweb.entrenador.repository;

import com.sigaweb.entrenador.entities.Evaluacion;
import com.sigaweb.entrenador.entities.TipoEvaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluacionesRepository extends JpaRepository<Evaluacion, Integer> {
    Evaluacion findByEstado(Short estado);
}
