package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.Componentes;
import com.sigaweb.entrenador.repository.ComponentesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ComponenteServiceImpl implements ComponentesService {

    @Autowired
    private ComponentesRepository componentesRepository;


    @Override
    public void saveComponentes(Componentes componente) {
        componentesRepository.save(componente);
    }

    @Override
    public void deleteComponentes(Componentes componente) {
        componentesRepository.delete(componente);
    }

    @Override
    public Page<Componentes> findAllComponentes(Pageable page) {
        return componentesRepository.findAll(page);
    }

    @Override
    public Componentes findByNombre(String nombre) {
        return componentesRepository.findByNombre(nombre);
    }

    @Override
    public List<Componentes> findByEstado(Short estado) {
        return componentesRepository.findAllByEstadoEquals(estado);
    }

    @Override
    public Componentes findById(Integer id) {
        Optional<Componentes> optional = componentesRepository.findById(id);
        return optional.orElse(null);
    }
}
