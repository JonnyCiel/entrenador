package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.Areas;
import com.sigaweb.entrenador.entities.Competencias;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.awt.*;
import java.awt.geom.Area;
import java.util.List;

public interface AreasService {
    void saveAreas(Areas competencia);

    void deleteAreas(Areas competencia);

    List<Areas> findAllAreas();

    List<Areas> findAllByEstado(Short estado);

    Page<Areas> findAllAreas(Pageable page);

    Areas findByNombre(String nombre);

    Areas findById(Integer id);
}
