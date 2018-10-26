package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.PreguntaGrado;
import com.sigaweb.entrenador.repository.RelacionGradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PreguntaGradoServiceImpl implements RelacionGradoService {

    @Autowired
    private RelacionGradoRepository relacionGradoRepository;

    @Override
    public void saveRelacionGrado(PreguntaGrado relacion) {
        relacionGradoRepository.save(relacion);
    }

    @Override
    public void deleteRelacionGrado(PreguntaGrado relacion) {
        relacionGradoRepository.delete(relacion);
    }

    @Override
    public List<PreguntaGrado> findAllRelacionGrado() {
        return relacionGradoRepository.findAll();
    }

    @Override
    public List<PreguntaGrado> findByIdPreguntaAndIdGrado(Integer idPregunta, Integer idGrado) {
        return relacionGradoRepository.findByIdPreguntaIdPreguntaAndIdGradoIdGrado(idPregunta, idGrado);
    }

    @Override
    public List<PreguntaGrado> findAllByEstado(Short estado) {
        return relacionGradoRepository.findAllByEstadoEquals(estado);
    }

    @Override
    public List<PreguntaGrado> findAllByIdPregunta(Integer idPregunta) {
        return relacionGradoRepository.findAllByIdPreguntaIdPregunta(idPregunta);
    }

    @Override
    public Page<PreguntaGrado> findAllRelacionGrado(Pageable page) {
        return relacionGradoRepository.findAll(page);
    }

    @Override
    public PreguntaGrado findByIdPregunta(Integer id) {
        return relacionGradoRepository.findByIdPreguntaIdPregunta(id);
    }

    @Override
    public PreguntaGrado findById(Integer id) {
        Optional<PreguntaGrado> optional = relacionGradoRepository.findById(id);
        return optional.orElse(null);
    }
}
