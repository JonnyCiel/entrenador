package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.Competencias;
import com.sigaweb.entrenador.entities.Componentes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ComponentesService {
    void saveComponentes(Componentes componente);

    void deleteComponentes(Componentes componente);

    Page<Componentes> findAllComponentes(Pageable page);

    Componentes findByNombre(String nombre);

    List<Componentes> findByEstado(Short estado);

    Componentes findById(Integer id);
}
