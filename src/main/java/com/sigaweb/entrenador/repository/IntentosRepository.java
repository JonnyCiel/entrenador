package com.sigaweb.entrenador.repository;

import com.sigaweb.entrenador.entities.Intentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IntentosRepository extends JpaRepository<Intentos, Integer> {
    List<Intentos> findAllByEstadoEquals(Short estado);
    Optional<Intentos> findByIdEvalUsuarioIdEvalUsuarioEquals(Integer id);
}
