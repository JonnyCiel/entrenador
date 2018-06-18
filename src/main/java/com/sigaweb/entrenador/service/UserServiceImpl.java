package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.Usuario;
import com.sigaweb.entrenador.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Usuario findByEmail(String email) {
        return userRepository.findByCorreo(email);
    }
}
