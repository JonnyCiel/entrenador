package com.sigaweb.entrenador.repository;

import com.sigaweb.entrenador.entities.Competencias;
import com.sigaweb.entrenador.entities.Componentes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComponentesRepository extends JpaRepository<Componentes, Integer> {
    Componentes findByNombre(String nombre);
    List<Componentes> findAllByEstadoEquals(Short estado);
}
