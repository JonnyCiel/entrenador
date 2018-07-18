package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.Evaluacion;
import com.sigaweb.entrenador.entities.EvaluacionPreguntas;
import com.sigaweb.entrenador.entities.Preguntas;
import com.sigaweb.entrenador.repository.EvaluacionPreguntasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluacionPreguntasServiceImpl implements EvaluacionPreguntasService {

    @Autowired
    private EvaluacionPreguntasRepository evaluacionPreguntasRepository;


    @Override
    public void saveEvaluacionPreguntas(EvaluacionPreguntas evaluacionPregunta) {
        evaluacionPreguntasRepository.save(evaluacionPregunta);
    }

    @Override
    public void deleteEvaluacionPreguntas(EvaluacionPreguntas evaluacionPregunta) {
        evaluacionPreguntasRepository.delete(evaluacionPregunta);
    }

    @Override
    public Page<EvaluacionPreguntas> findAllEvaluacionPreguntas(Pageable page) {
        return evaluacionPreguntasRepository.findAll(page);
    }

    @Override
    public EvaluacionPreguntas findById(Integer id) {
        Optional<EvaluacionPreguntas> optional = evaluacionPreguntasRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public List<EvaluacionPreguntas> findByidEvaluacion(Integer id) {
        return evaluacionPreguntasRepository.findAllByEvaluacionIdIdEvaluacion(id);
    }
}
