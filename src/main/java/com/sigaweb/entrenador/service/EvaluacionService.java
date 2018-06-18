package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.Evaluacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EvaluacionService {

    void saveEvaluacion(Evaluacion evaluacion);

    void deleteEvaluacion(Evaluacion evaluacion);

    Page<Evaluacion> findAllEvaluaciones(Pageable page);

    Evaluacion findByEstado(Short estado);

    Evaluacion findById(Integer id);
}
