package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.Evaluacion;
import com.sigaweb.entrenador.repository.EvaluacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EvaluacionesServiceImpl implements EvaluacionService {

    @Autowired
    private EvaluacionesRepository evaluacionesRepository;

    @Override
    public void saveEvaluacion(Evaluacion evaluacion) {
        evaluacionesRepository.save(evaluacion);
    }

    @Override
    public void deleteEvaluacion(Evaluacion evaluacion) {
        evaluacionesRepository.delete(evaluacion);
    }

    @Override
    public Page<Evaluacion> findAllEvaluaciones(Pageable page) {
        return evaluacionesRepository.findAll(page);
    }

    @Override
    public Evaluacion findByEstado(Short estado) {
        return evaluacionesRepository.findByEstado(estado);
    }

    @Override
    public Evaluacion findById(Integer id) {
        Optional<Evaluacion> optional = evaluacionesRepository.findById(id);
        return optional.orElse(null);
    }
}
