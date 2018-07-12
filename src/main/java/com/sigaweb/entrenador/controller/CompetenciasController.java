package com.sigaweb.entrenador.controller;

import com.sigaweb.entrenador.entities.Competencias;
import com.sigaweb.entrenador.service.CompetenciaService;
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
@RequestMapping("competencias")
public class CompetenciasController {

    @Autowired
    private UserService userService;

    @Autowired
    private CompetenciaService competenciaService;

    @GetMapping("index")
    public String indexCompetencias(@PageableDefault(value = 10, page = 0) Pageable page, Model model,
                                    Authentication authentication) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("competencias", competenciaService.findAllCompetencias(page));
        return "competenciasList";
    }


    @GetMapping("form")
    public String competenciaForm(Authentication authentication, Model model) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("competencia", new Competencias());

        return "competenciaForm";
    }


    @PostMapping("save")
    public String saveForm(@Valid @ModelAttribute Competencias competencia, BindingResult bindingResult, Model model,
                           RedirectAttributes ra, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
            model.addAttribute("errores", bindingResult.getAllErrors());
            return "competenciaForm";
        }

        competenciaService.saveCompetencia(competencia);

        ra.addFlashAttribute("mensaje", "Se ha guardado con éxito");

        return "redirect:/competencias/index";
    }


    @GetMapping("edit/{id}")
    public String editCompetencia(@PathVariable("id") Integer id, Model model, Authentication authentication) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("competencia", competenciaService.findById(id));
        return "competenciaForm";
    }

    @GetMapping("delete/{id}")
    public String deleteCompetencia(@PathVariable("id") Integer id, RedirectAttributes ra) {
        competenciaService.deleteCompetencia(competenciaService.findById(id));
        ra.addFlashAttribute("mensaje", "Se ha eliminado con éxito");
        return "redirect:/competencias/index";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
    }
}
