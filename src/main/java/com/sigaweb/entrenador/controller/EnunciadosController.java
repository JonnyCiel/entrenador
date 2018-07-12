package com.sigaweb.entrenador.controller;

import com.sigaweb.entrenador.entities.Enunciados;
import com.sigaweb.entrenador.service.EnunciadoService;
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
@RequestMapping("enunciados")
public class EnunciadosController {

    @Autowired
    private UserService userService;

    @Autowired
    private EnunciadoService enunciadoService;

    @GetMapping("index")
    public String indexCompetencias(@PageableDefault(value = 10, page = 0) Pageable page, Model model,
                                    Authentication authentication) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("enunciados", enunciadoService.findAllEnunciados(page));
        return "enunciadosList";
    }


    @GetMapping("form")
    public String competenciaForm(Authentication authentication, Model model) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("enunciado", new Enunciados());

        return "enunciadosForm";
    }


    @PostMapping("save")
    public String saveForm(@Valid @ModelAttribute Enunciados enunciado, BindingResult bindingResult, Model model,
                           RedirectAttributes ra, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
            model.addAttribute("errores", bindingResult.getAllErrors());
            return "enunciadosForm";
        }

        enunciadoService.saveEnunciados(enunciado);

        ra.addFlashAttribute("mensaje", "Se ha guardado con éxito");

        return "redirect:/preguntas/index";
    }


    @GetMapping("edit/{id}")
    public String editCompetencia(@PathVariable("id") Integer id, Model model, Authentication authentication) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("enunciado", enunciadoService.findById(id));
        return "enunciadosForm";
    }

    @GetMapping("delete/{id}")
    public String deleteCompetencia(@PathVariable("id") Integer id, RedirectAttributes ra) {
        enunciadoService.deleteEnunciados(enunciadoService.findById(id));
        ra.addFlashAttribute("mensaje", "Se ha eliminado con éxito");
        return "redirect:/enunciados/index";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
    }
}
