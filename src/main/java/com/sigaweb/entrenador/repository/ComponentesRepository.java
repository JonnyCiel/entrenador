package com.sigaweb.entrenador.repository;

import com.sigaweb.entrenador.entities.Areas;
import com.sigaweb.entrenador.entities.Componentes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponentesRepository extends JpaRepository<Componentes, Integer> {
    Componentes findByNombre(String nombre);
}
