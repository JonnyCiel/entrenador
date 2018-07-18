package com.sigaweb.entrenador.repository;

import com.sigaweb.entrenador.entities.RespuestasIntentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestaIntentosRepository extends JpaRepository<RespuestasIntentos, Integer> {
    RespuestasIntentos findByIntentoIdIdIntentoEquals(Integer id);
}
