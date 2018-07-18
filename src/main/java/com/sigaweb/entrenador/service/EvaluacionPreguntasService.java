package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.CompetenciaComponente;
import com.sigaweb.entrenador.entities.EvaluacionPreguntas;
import com.sigaweb.entrenador.entities.Preguntas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EvaluacionPreguntasService {
    void saveEvaluacionPreguntas(EvaluacionPreguntas evaluacionPregunta);

    void deleteEvaluacionPreguntas(EvaluacionPreguntas evaluacionPregunta);

    Page<EvaluacionPreguntas> findAllEvaluacionPreguntas(Pageable page);

    EvaluacionPreguntas findById(Integer id);

    List<EvaluacionPreguntas> findByidEvaluacion(Integer id);
}
