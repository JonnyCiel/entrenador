package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.Areas;
import com.sigaweb.entrenador.entities.Componentes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ComponentesService {
    void saveComponentes(Componentes componente);

    void deleteComponentes(Componentes componente);

    Page<Componentes> findAllComponentes(Pageable page);

    Componentes findByNombre(String nombre);

    Componentes findById(Integer id);
}
