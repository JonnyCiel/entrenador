package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.PreguntaGrado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RelacionGradoService {

    void saveRelacionGrado(PreguntaGrado relacion);

    void deleteRelacionGrado(PreguntaGrado relacion);

    List<PreguntaGrado> findAllRelacionGrado();

    List<PreguntaGrado> findByIdPreguntaAndIdGrado(Integer idPregunta, Integer idGrado);

    List<PreguntaGrado> findAllByEstado(Short estado);

    List<PreguntaGrado> findAllByIdPregunta(Integer idPregunta);

    Page<PreguntaGrado> findAllRelacionGrado(Pageable page);

    PreguntaGrado findByIdPregunta(Integer id);

    PreguntaGrado findById(Integer id);
}
