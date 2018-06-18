package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.Competencias;
import com.sigaweb.entrenador.entities.Grados;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompetenciaService {
    void saveCompetencia(Competencias competencia);

    void deleteCompetencia(Competencias competencia);

    Page<Competencias> findAllCompetencias(Pageable page);

    Competencias findByNombre(String numero);

    Competencias findById(Integer id);
}
