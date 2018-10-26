package com.sigaweb.entrenador.repository;

import com.sigaweb.entrenador.entities.PreguntaGrado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelacionGradoRepository extends JpaRepository<PreguntaGrado, Integer> {
    PreguntaGrado findByIdPreguntaIdPregunta(Integer id);

    List<PreguntaGrado> findAllByEstadoEquals(Short estado);

    List<PreguntaGrado> findByIdPreguntaIdPreguntaAndIdGradoIdGrado(Integer idPregunta, Integer idGrado);

    List<PreguntaGrado> findAllByIdPreguntaIdPregunta(Integer idGrado);
}
