package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.Preguntas;
import com.sigaweb.entrenador.entities.Respuesta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RespuestasService {
    void saveRespuesta(Respuesta respuesta);

    void deleteRespuesta(Respuesta respuesta);

    Page<Respuesta> findAllRespuesta(Pageable page);

    Respuesta findById(Integer id);

    List<Respuesta> findAllByEstado(Short estado);

    List<Respuesta> findAllByIdPreguntaEquals(Integer id);
}
