package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.EvaluacionUsuario;
import com.sigaweb.entrenador.repository.EvaluacionUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EvaluacionUsuarioServiceImpl implements EvaluacionUsuarioService {

    @Autowired
    private EvaluacionUsuarioRepository evaluacionUsuarioRepository;

    @Override
    public void saveEvaluacionUsuario(EvaluacionUsuario evaluacion) {
        evaluacionUsuarioRepository.save(evaluacion);
    }

    @Override
    public void deleteEvaluacionUsuario(EvaluacionUsuario evaluacion) {
        evaluacionUsuarioRepository.delete(evaluacion);
    }

    @Override
    public Page<EvaluacionUsuario> findAllEvaluacionUsuario(Pageable page) {
        return evaluacionUsuarioRepository.findAll(page);
    }

    @Override
    public EvaluacionUsuario findByIdusuario(Integer id) {
        return evaluacionUsuarioRepository.findByIdUsuarioIdUsuarioEquals(id);
    }

    @Override
    public EvaluacionUsuario findByIdEvaluacion(Integer id) {
        return evaluacionUsuarioRepository.findByIdEvaluacionIdEvaluacionEquals(id);
    }

    @Override
    public EvaluacionUsuario findByidUsuarioAndIdevaluacion(Integer idUsuario, Integer idEvaluacion) {
        return evaluacionUsuarioRepository.findByIdUsuarioIdUsuarioAndIdEvaluacionIdEvaluacion(idUsuario, idEvaluacion);
    }

    @Override
    public EvaluacionUsuario findById(Integer id) {
        Optional<EvaluacionUsuario> optional = evaluacionUsuarioRepository.findById(id);
        return optional.orElse(null);
    }
}
