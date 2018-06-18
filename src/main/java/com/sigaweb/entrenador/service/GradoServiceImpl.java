package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.Grados;
import com.sigaweb.entrenador.repository.GradoRepository;
import org.hibernate.HibernateError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GradoServiceImpl implements GradoService {

    @Autowired
    private GradoRepository gradoRepository;

    @Override
    public String saveGrado(Grados grado) {
        try {
            gradoRepository.save(grado);
            return "ok";
        } catch (HibernateError error) {
            return error.getLocalizedMessage();
        }
    }

    @Override
    public String deleteGrado(Grados grado) {
        try {
            gradoRepository.delete(grado);
            return "ok";
        } catch (HibernateError error) {
            return error.getLocalizedMessage();
        }
    }

    @Override
    public Page<Grados> findAllGrados(Pageable page) {
        return gradoRepository.findAll(page);
    }

    @Override
    public String updateGrado(int id) {
        try {
            Optional<Grados> grado = gradoRepository.findById(id);
            if (grado.isPresent()) {
                gradoRepository.save(grado.get());
                return "ok";
            } else {
                return "not found";
            }
        } catch (HibernateError error) {
            return error.getLocalizedMessage();
        }
    }

    @Override
    public Grados findByNumero(String numero) {
        return gradoRepository.findByNumero(numero);
    }

    @Override
    public Grados findById(Integer id) {
        Optional<Grados> optional = gradoRepository.findById(id);
        return optional.orElse(null);
    }
}
