package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.Enunciados;
import com.sigaweb.entrenador.repository.EnunciadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnunciadoServiceImpl implements EnunciadoService {

    @Autowired
    private EnunciadosRepository enunciadosRepository;

    @Override
    public void saveEnunciados(Enunciados enunciado) {
        enunciadosRepository.save(enunciado);
    }

    @Override
    public void deleteEnunciados(Enunciados enunciado) {
        enunciadosRepository.delete(enunciado);
    }

    @Override
    public Page<Enunciados> findAllEnunciados(Pageable page) {
        return enunciadosRepository.findAll(page);
    }

    @Override
    public Enunciados findById(Integer id) {
        Optional<Enunciados> optional = enunciadosRepository.findById(id);
        return optional.orElse(null);
    }
}
