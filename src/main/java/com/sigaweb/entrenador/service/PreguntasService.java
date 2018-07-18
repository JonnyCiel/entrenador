package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.CompetenciaComponente;
import com.sigaweb.entrenador.entities.Preguntas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PreguntasService {
    void savePregunta(Preguntas pregunta);

    void deletePregunta(Preguntas pregunta);

    Page<Preguntas> findAllPregunta(Pageable page);

    Preguntas findById(Integer id);

    List<Preguntas> findAllByEstado(Short estado);

    List<Preguntas> findByNivelAndProfundizacion(Short nivel, Short profundizacion);
}
