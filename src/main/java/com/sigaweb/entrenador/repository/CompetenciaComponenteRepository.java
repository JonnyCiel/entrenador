package com.sigaweb.entrenador.repository;

import com.sigaweb.entrenador.entities.CompetenciaComponente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetenciaComponenteRepository extends JpaRepository<CompetenciaComponente, Integer> {
    List<CompetenciaComponente> findAllByEstadoEquals(Short estado);
}
