package com.sigaweb.entrenador.controller;

import com.sigaweb.entrenador.entities.Grados;
import com.sigaweb.entrenador.service.GradoService;
import com.sigaweb.entrenador.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("grados")
public class GradosController {

    @Autowired
    private GradoService gradoService;

    @Autowired
    private UserService userService;

    @GetMapping("index")
    public String gradoList(@PageableDefault(value = 10, page = 0) Pageable page, Model model,
                            Authentication authentication) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("grados", gradoService.findAllGrados(page));
        return "gradoList";
    }

    @GetMapping("form")
    public String gradoForm(Model model, Authentication authentication) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("grado", new Grados());
        return "gradoForm";
    }


    @PostMapping("save")
    public String saveForm(@Valid @ModelAttribute Grados grado, BindingResult bindingResult, Model model,
                           RedirectAttributes ra) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errores", bindingResult.getAllErrors());
            return "gradoForm";
        }

        gradoService.saveGrado(grado);

        ra.addFlashAttribute("mensaje", "Se ha guardado con éxito");

        return "redirect:/grados/index";
    }

    @GetMapping("edit/{id}")
    public String editForm(Model model, @PathVariable("id") Integer id, Authentication authentication) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("grado", gradoService.findById(id));
        return "gradoForm";
    }

    @GetMapping("delete/{id}")
    public String deleteGrado(@PathVariable("id") Integer id, RedirectAttributes ra) {
        gradoService.deleteGrado(gradoService.findById(id));
        ra.addFlashAttribute("mensaje", "Se ha eliminado con éxito");
        return "redirect:/grados/index";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
    }

}
