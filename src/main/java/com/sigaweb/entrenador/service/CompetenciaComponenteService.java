package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.CompetenciaComponente;
import com.sigaweb.entrenador.entities.Competencias;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompetenciaComponenteService {
    void saveCompetenciaComponente(CompetenciaComponente competenciaComponente);

    void deleteCompetenciaComponente(CompetenciaComponente competenciaComponente);

    Page<CompetenciaComponente> findAllCompetenciasComponente(Pageable page);

    CompetenciaComponente findById(Integer id);

    List<CompetenciaComponente> findAllByEstado(Short estado);
}
