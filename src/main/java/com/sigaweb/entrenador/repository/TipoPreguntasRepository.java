package com.sigaweb.entrenador.repository;

import com.sigaweb.entrenador.entities.TipoPreguntas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoPreguntasRepository extends JpaRepository<TipoPreguntas, Integer> {
    TipoPreguntas findByNombre(String nombre);

    List<TipoPreguntas> findAllByEstadoEquals(Short estado);
}
