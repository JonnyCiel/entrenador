package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.CompetenciaComponente;
import com.sigaweb.entrenador.repository.CompetenciaComponenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompetenciaComponenteServiceImpl implements CompetenciaComponenteService {


    @Autowired
    private CompetenciaComponenteRepository competenciaComponenteRepository;


    @Override
    public void saveCompetenciaComponente(CompetenciaComponente competenciaComponente) {
        competenciaComponenteRepository.save(competenciaComponente);
    }

    @Override
    public void deleteCompetenciaComponente(CompetenciaComponente competenciaComponente) {
        competenciaComponenteRepository.delete(competenciaComponente);
    }

    @Override
    public Page<CompetenciaComponente> findAllCompetenciasComponente(Pageable page) {
        return competenciaComponenteRepository.findAll(page);
    }

    @Override
    public CompetenciaComponente findById(Integer id) {
        Optional<CompetenciaComponente> optional = competenciaComponenteRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public List<CompetenciaComponente> findAllByEstado(Short estado) {
        return competenciaComponenteRepository.findAllByEstadoEquals(estado);
    }
}
