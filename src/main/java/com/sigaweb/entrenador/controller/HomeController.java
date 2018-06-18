package com.sigaweb.entrenador.controller;

import com.sigaweb.entrenador.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String index(Authentication authentication, Model model) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
//        for (GrantedAuthority rol : authentication.getAuthorities()) {
//            System.out.println("Rol: " + rol.getAuthority());
//            System.out.println(authentication.getName());
//        }
        return "home";
    }
}
