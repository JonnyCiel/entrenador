package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.Grados;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GradoService {
    String saveGrado(Grados grado);

    String deleteGrado(Grados grado);

    Page<Grados> findAllGrados(Pageable page);

    String updateGrado(int id);

    List<Grados> findAll();

    Grados findByNumero(String numero);

    Grados findById(Integer id);
}
