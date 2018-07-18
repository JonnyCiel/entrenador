package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.Intentos;
import com.sigaweb.entrenador.entities.RespuestasIntentos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RespuestaIntentoService {

    void saveRespuestasIntentos(RespuestasIntentos respuestasIntentos);

    void deleteRespuestasIntentos(RespuestasIntentos respuestasIntentos);

    List<RespuestasIntentos> findAllRespuestasIntentos();

    Page<RespuestasIntentos> findAllRespuestasIntentos(Pageable page);

    RespuestasIntentos findById(Integer id);

    RespuestasIntentos findByIdintento(Integer id);
}
