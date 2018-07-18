package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.RespuestasIntentos;
import com.sigaweb.entrenador.repository.RespuestaIntentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RespuestaIntentoServiceImpl implements RespuestaIntentoService {


    @Autowired
    private RespuestaIntentosRepository respuestaIntentosRepository;

    @Override
    public void saveRespuestasIntentos(RespuestasIntentos respuestasIntentos) {
        respuestaIntentosRepository.save(respuestasIntentos);
    }

    @Override
    public void deleteRespuestasIntentos(RespuestasIntentos respuestasIntentos) {
        respuestaIntentosRepository.delete(respuestasIntentos);
    }

    @Override
    public List<RespuestasIntentos> findAllRespuestasIntentos() {
        return respuestaIntentosRepository.findAll();
    }

    @Override
    public Page<RespuestasIntentos> findAllRespuestasIntentos(Pageable page) {
        return respuestaIntentosRepository.findAll(page);
    }

    @Override
    public RespuestasIntentos findById(Integer id) {
        Optional<RespuestasIntentos> optional = respuestaIntentosRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public RespuestasIntentos findByIdintento(Integer id) {
        return respuestaIntentosRepository.findByIntentoIdIdIntentoEquals(id);
    }
}
