package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.Competencias;
import com.sigaweb.entrenador.entities.Grados;
import com.sigaweb.entrenador.repository.CompetenciasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CompetenciaServiceImpl implements CompetenciaService {

    @Autowired
    private CompetenciasRepository competenciasRepository;

    @Override
    public void saveCompetencia(Competencias competencia) {
        competenciasRepository.save(competencia);
    }

    @Override
    public void deleteCompetencia(Competencias competencia) {
        competenciasRepository.delete(competencia);
    }

    @Override
    public Page<Competencias> findAllCompetencias(Pageable page) {
        return competenciasRepository.findAll(page);
    }

    @Override
    public Competencias findByNombre(String numero) {
        return competenciasRepository.findByNombre(numero);
    }

    @Override
    public Competencias findById(Integer id) {
        Optional<Competencias> optional = competenciasRepository.findById(id);
        return optional.orElse(null);
    }
}
