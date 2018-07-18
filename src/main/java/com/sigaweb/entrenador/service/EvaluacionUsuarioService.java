package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.EvaluacionUsuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EvaluacionUsuarioService {

    void saveEvaluacionUsuario(EvaluacionUsuario evaluacion);

    void deleteEvaluacionUsuario(EvaluacionUsuario evaluacion);

    Page<EvaluacionUsuario> findAllEvaluacionUsuario(Pageable page);

    EvaluacionUsuario findByIdusuario(Integer id);

    EvaluacionUsuario findByIdEvaluacion(Integer id);

    EvaluacionUsuario findByidUsuarioAndIdevaluacion(Integer idUsuario, Integer idEvaluacion);

    EvaluacionUsuario findById(Integer id);
}
