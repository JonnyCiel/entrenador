package com.sigaweb.entrenador.repository;

import com.sigaweb.entrenador.entities.Areas;
import com.sigaweb.entrenador.entities.TipoEvaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoEvaluacionRepository extends JpaRepository<TipoEvaluacion, Integer> {
    TipoEvaluacion findByNombre(String nombre);
    List<TipoEvaluacion> findAllByEstadoEquals(Short estado);
}
