package com.sigaweb.entrenador.repository;

import com.sigaweb.entrenador.entities.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespuestasRepository extends JpaRepository<Respuesta, Integer> {
    List<Respuesta> findAllByEstadoEquals(Short estado);

    List<Respuesta> findAllByIdPreguntaIdPregunta(Integer id);
}
