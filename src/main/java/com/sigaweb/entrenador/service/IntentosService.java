package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.Areas;
import com.sigaweb.entrenador.entities.Intentos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IntentosService {
    void saveIntentos(Intentos intento);

    void deleteIntentos(Intentos intento);

    List<Intentos> findAllIntentos();

    List<Intentos> findAllByEstado(Short estado);

    Page<Intentos> findAllIntentos(Pageable page);

    Intentos findById(Integer id);

    Intentos findByUsuariosEval(Integer id);
}
