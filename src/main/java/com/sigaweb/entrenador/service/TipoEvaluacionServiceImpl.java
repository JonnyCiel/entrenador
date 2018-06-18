package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.Areas;
import com.sigaweb.entrenador.entities.TipoEvaluacion;
import com.sigaweb.entrenador.repository.TipoEvaluacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoEvaluacionServiceImpl implements TipoEvaluacionService {

    @Autowired
    private TipoEvaluacionRepository tipoEvaluacionRepository;

    @Override
    public void saveTipoEvaluacion(TipoEvaluacion tipoEvaluacion) {
        tipoEvaluacionRepository.save(tipoEvaluacion);
    }

    @Override
    public void deleteTipoEvaluacion(TipoEvaluacion tipoEvaluacion) {
        tipoEvaluacionRepository.delete(tipoEvaluacion);
    }

    @Override
    public List<TipoEvaluacion> findAllTipoEvaluacion() {
        return tipoEvaluacionRepository.findAll();
    }

    @Override
    public List<TipoEvaluacion> findAllByEstado(Short estado) {
        return tipoEvaluacionRepository.findAllByEstadoEquals(estado);
    }

    @Override
    public Page<TipoEvaluacion> findAllTipoEvaluacion(Pageable page) {
        return tipoEvaluacionRepository.findAll(page);
    }

    @Override
    public TipoEvaluacion findByNombre(String nombre) {
        return tipoEvaluacionRepository.findByNombre(nombre);
    }

    @Override
    public TipoEvaluacion findById(Integer id) {
        Optional<TipoEvaluacion> optional = tipoEvaluacionRepository.findById(id);
        return optional.orElse(null);
    }
}
