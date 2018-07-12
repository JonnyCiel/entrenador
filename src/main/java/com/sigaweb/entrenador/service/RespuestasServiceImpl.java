package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.Respuesta;
import com.sigaweb.entrenador.repository.RespuestasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RespuestasServiceImpl implements RespuestasService {

    @Autowired
    private RespuestasRepository respuestasRepository;


    @Override
    public void saveRespuesta(Respuesta respuesta) {
        respuestasRepository.save(respuesta);
    }

    @Override
    public void deleteRespuesta(Respuesta respuesta) {
        respuestasRepository.delete(respuesta);
    }

    @Override
    public Page<Respuesta> findAllRespuesta(Pageable page) {
        return respuestasRepository.findAll(page);
    }

    @Override
    public Respuesta findById(Integer id) {
        Optional<Respuesta> optional = respuestasRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public List<Respuesta> findAllByEstado(Short estado) {
        return respuestasRepository.findAllByEstadoEquals(estado);
    }

    @Override
    public List<Respuesta> findAllByIdPreguntaEquals(Integer id) {
        return respuestasRepository.findAllByIdPreguntaIdPregunta(id);
    }
}
