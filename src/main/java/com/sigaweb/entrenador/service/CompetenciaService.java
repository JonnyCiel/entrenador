package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.Competencias;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompetenciaService {
    void saveCompetencia(Competencias competencia);

    void deleteCompetencia(Competencias competencia);

    Page<Competencias> findAllCompetencias(Pageable page);

    Competencias findByNombre(String nombre);

    List<Competencias> findByEstado(Short estado);

    Competencias findById(Integer id);
}
