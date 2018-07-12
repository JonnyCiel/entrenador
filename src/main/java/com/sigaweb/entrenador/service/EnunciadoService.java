package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.Enunciados;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EnunciadoService {
    void saveEnunciados(Enunciados enunciado);

    void deleteEnunciados(Enunciados enunciado);

    Page<Enunciados> findAllEnunciados(Pageable page);

    Enunciados findById(Integer id);
}
