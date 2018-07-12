package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.Areas;
import com.sigaweb.entrenador.repository.AreasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreasServiceImpl implements AreasService {

    @Autowired
    private AreasRepository areasRepository;

    @Override
    public void saveAreas(Areas competencia) {
        areasRepository.save(competencia);
    }

    @Override
    public void deleteAreas(Areas competencia) {
        areasRepository.delete(competencia);
    }

    @Override
    public List<Areas> findAllAreas() {
        return areasRepository.findAll();
    }

    @Override
    public List<Areas> findAllByEstado(Short estado) {
        return areasRepository.findAllByEstadoEquals(estado);
    }


    @Override
    public Page<Areas> findAllAreas(Pageable page) {
        return areasRepository.findAll(page);
    }

    @Override
    public Areas findByNombre(String nombre) {
        return areasRepository.findByNombre(nombre);
    }

    @Override
    public Areas findById(Integer id) {
        Optional<Areas> optional = areasRepository.findById(id);
        return optional.orElse(null);
    }
}
