package com.sigaweb.entrenador.repository;

import com.sigaweb.entrenador.entities.Areas;
import com.sigaweb.entrenador.entities.Competencias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreasRepository extends JpaRepository<Areas, Integer> {
    Areas findByNombre(String nombre);
    List<Areas> findAllByEstadoEquals(Short estado);
}
