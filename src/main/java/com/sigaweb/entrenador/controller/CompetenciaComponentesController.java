package com.sigaweb.entrenador.controller;

import com.sigaweb.entrenador.entities.CompetenciaComponente;
import com.sigaweb.entrenador.entities.Competencias;
import com.sigaweb.entrenador.entities.Componentes;
import com.sigaweb.entrenador.service.CompetenciaComponenteService;
import com.sigaweb.entrenador.service.CompetenciaService;
import com.sigaweb.entrenador.service.ComponentesService;
import com.sigaweb.entrenador.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("competecompone")
public class CompetenciaComponentesController {

    @Autowired
    private UserService userService;

    @Autowired
    private CompetenciaComponenteService competenciaComponenteService;

    @Autowired
    private ComponentesService componentesService;

    @Autowired
    private CompetenciaService competenciaService;

    @GetMapping("index")
    public String indexCompeteCompone(@PageableDefault(value = 10, page = 0) Pageable page, Model model,
                                      Authentication authentication) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("competescompones", competenciaComponenteService.findAllCompetenciasComponente(page));


        return "competenciascomponentesList";
    }

    @GetMapping("form")
    public String competenciaForm(Authentication authentication, Model model,
                                  @ModelAttribute("competencias") List<Competencias> competencia,
                                  @ModelAttribute("componentes") List<Componentes> componente) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("competecompone", new CompetenciaComponente());

        return "competenciacomponenteForm";
    }

    @ModelAttribute("competencias")
    public List<Competencias> getCompetencias() {
        return competenciaService.findByEstado((short) 1);
    }

    @ModelAttribute("componentes")
    public List<Componentes> getComponentes() {
        return componentesService.findByEstado((short) 1);
    }


    @PostMapping("save")
    public String saveForm(@Valid @ModelAttribute CompetenciaComponente competecompone,
                           BindingResult bindingResult, Model model,
                           RedirectAttributes ra, Authentication authentication,
                           @ModelAttribute("competencias") List<Competencias> competenciasList,
                           @ModelAttribute("componentes") List<Componentes> componente) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
            model.addAttribute("errores", bindingResult.getAllErrors());
            return "competenciacomponenteForm";
        }

        competenciaComponenteService.saveCompetenciaComponente(competecompone);

        ra.addFlashAttribute("mensaje", "Se ha guardado con éxito");

        return "redirect:/competecompone/index";
    }


    @GetMapping("edit/{id}")
    public String editCompetencia(@PathVariable("id") Integer id, Model model,
                                  Authentication authentication,
                                  @ModelAttribute("competencias") List<Competencias> competenciasList,
                                  @ModelAttribute("componentes") List<Componentes> componente) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("competecompone", competenciaComponenteService.findById(id));
        return "competenciacomponenteForm";
    }

    @GetMapping("delete/{id}")
    public String deleteCompetencia(@PathVariable("id") Integer id, RedirectAttributes ra) {
        competenciaComponenteService.deleteCompetenciaComponente(competenciaComponenteService.findById(id));
        ra.addFlashAttribute("mensaje", "Se ha eliminado con éxito");
        return "redirect:/competecompone/index";
    }
}
