package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.TipoEvaluacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TipoEvaluacionService {
    void saveTipoEvaluacion(TipoEvaluacion tipoEvaluacion);

    void deleteTipoEvaluacion(TipoEvaluacion tipoEvaluacion);

    List<TipoEvaluacion> findAllTipoEvaluacion();

    List<TipoEvaluacion> findAllByEstado(Short estado);

    Page<TipoEvaluacion> findAllTipoEvaluacion(Pageable page);

    TipoEvaluacion findByNombre(String nombre);

    TipoEvaluacion findById(Integer id);
}
