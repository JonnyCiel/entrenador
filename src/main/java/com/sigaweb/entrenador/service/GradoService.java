package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.Grados;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GradoService {
    String saveGrado(Grados grado);

    String deleteGrado(Grados grado);

    Page<Grados> findAllGrados(Pageable page);

    String updateGrado(int id);

    Grados findByNumero(String numero);

    Grados findById(Integer id);
}
