package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.TipoPreguntas;
import com.sigaweb.entrenador.repository.TipoPreguntasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoPreguntasServiceImpl implements TipoPreguntasService {

    @Autowired
    private TipoPreguntasRepository tipoPreguntasRepository;

    @Override
    public void saveTipoPreguntas(TipoPreguntas tipoPregunta) {
        tipoPreguntasRepository.save(tipoPregunta);
    }

    @Override
    public void deleteTipoPreguntas(TipoPreguntas tipoPregunta) {
        tipoPreguntasRepository.delete(tipoPregunta);
    }

    @Override
    public List<TipoPreguntas> findAllTipoPreguntas() {
        return tipoPreguntasRepository.findAll();
    }

    @Override
    public List<TipoPreguntas> findAllByEstado(Short estado) {
        return tipoPreguntasRepository.findAllByEstadoEquals(estado);
    }

    @Override
    public Page<TipoPreguntas> findAllTipoPreguntas(Pageable page) {
        return tipoPreguntasRepository.findAll(page);
    }

    @Override
    public TipoPreguntas findByNombre(String nombre) {
        return tipoPreguntasRepository.findByNombre(nombre);
    }

    @Override
    public TipoPreguntas findById(Integer id) {
        Optional<TipoPreguntas> optional = tipoPreguntasRepository.findById(id);
        return optional.orElse(null);
    }
}
