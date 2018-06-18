package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.Areas;
import com.sigaweb.entrenador.entities.TipoPreguntas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TipoPreguntasService {
    void saveTipoPreguntas(TipoPreguntas tipoPregunta);

    void deleteTipoPreguntas(TipoPreguntas tipoPregunta);

    List<TipoPreguntas> findAllTipoPreguntas();

    List<TipoPreguntas> findAllByEstado(Short estado);

    Page<TipoPreguntas> findAllTipoPreguntas(Pageable page);

    TipoPreguntas findByNombre(String nombre);

    TipoPreguntas findById(Integer id);
}
