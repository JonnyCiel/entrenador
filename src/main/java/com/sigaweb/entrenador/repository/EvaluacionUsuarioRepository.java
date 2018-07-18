package com.sigaweb.entrenador.repository;

import com.sigaweb.entrenador.entities.Evaluacion;
import com.sigaweb.entrenador.entities.EvaluacionUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluacionUsuarioRepository extends JpaRepository<EvaluacionUsuario, Integer> {
    EvaluacionUsuario findByIdUsuarioIdUsuarioEquals(Integer id);
    EvaluacionUsuario findByIdEvaluacionIdEvaluacionEquals(Integer id);
    EvaluacionUsuario findByIdUsuarioIdUsuarioAndIdEvaluacionIdEvaluacion(Integer idU, Integer idEv);
}
