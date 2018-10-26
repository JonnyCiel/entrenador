package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.Preguntas;
import com.sigaweb.entrenador.repository.PreguntasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PreguntasServiceImpl implements PreguntasService {

    @Autowired
    private PreguntasRepository preguntasRepository;

    @Override
    public void savePregunta(Preguntas pregunta) {
        preguntasRepository.save(pregunta);
    }

    @Override
    public void deletePregunta(Preguntas pregunta) {
        preguntasRepository.delete(pregunta);
    }

    @Override
    public Preguntas findByName(String name) {
        return preguntasRepository.findByTextoEquals(name);
    }

    @Override
    public Page<Preguntas> findAllPregunta(Pageable page) {
        return preguntasRepository.findAll(page);
    }

    @Override
    public Preguntas findById(Integer id) {
        Optional<Preguntas> optional = preguntasRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public List<Preguntas> findAllByEstado(Short estado) {
        return preguntasRepository.findAllByEstadoEquals(estado);
    }

    @Override
    public List<Preguntas> findByNivelAndProfundizacion(Short nivel, Short profundizacion) {
        return preguntasRepository.findAllByNivelEqualsAndProfudizacionEquals(nivel, profundizacion);
    }
}
