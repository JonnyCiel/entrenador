package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.Intentos;
import com.sigaweb.entrenador.repository.IntentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IntentosServiceImpl implements IntentosService {

    @Autowired
    private IntentosRepository intentosRepository;

    @Override
    public void saveIntentos(Intentos intento) {
        intentosRepository.save(intento);
    }

    @Override
    public void deleteIntentos(Intentos intento) {
        intentosRepository.delete(intento);
    }

    @Override
    public List<Intentos> findAllIntentos() {
        return intentosRepository.findAll();
    }

    @Override
    public List<Intentos> findAllByEstado(Short estado) {
        return intentosRepository.findAllByEstadoEquals(estado);
    }

    @Override
    public Page<Intentos> findAllIntentos(Pageable page) {
        return intentosRepository.findAll(page);
    }

    @Override
    public Intentos findById(Integer id) {
        Optional<Intentos> optional = intentosRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public Intentos findByUsuariosEval(Integer id) {
        Optional<Intentos> optional = intentosRepository.findByIdEvalUsuarioIdEvalUsuarioEquals(id);
        return optional.orElse(null);
    }
}
