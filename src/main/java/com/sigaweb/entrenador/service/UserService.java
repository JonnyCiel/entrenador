package com.sigaweb.entrenador.service;

import com.sigaweb.entrenador.entities.Usuario;

public interface UserService {
    Usuario findByEmail(String email);
}
