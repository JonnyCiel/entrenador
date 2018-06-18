package com.sigaweb.entrenador.controller;

import com.sigaweb.entrenador.entities.Areas;
import com.sigaweb.entrenador.entities.Componentes;
import com.sigaweb.entrenador.service.AreasService;
import com.sigaweb.entrenador.service.ComponentesService;
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
@RequestMapping("componentes")
public class ComponentesController {

    @Autowired
    private UserService userService;

    @Autowired
    private ComponentesService componentesService;

    @Autowired
    private AreasService areasService;

    @GetMapping("index")
    public String indexArea(@PageableDefault(value = 10, page = 0) Pageable page, Model model,
                            Authentication authentication) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("componentes", componentesService.findAllComponentes(page));
        return "componentesList";
    }


    @GetMapping("form")
    public String areaForm(Authentication authentication, Model model) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("componente", new Componentes());
        model.addAttribute("areas", areasService.findAllByEstado((short) 1));

        return "componentesForm";
    }


    @PostMapping("save")
    public String saveForm(@Valid @ModelAttribute Componentes componente, BindingResult bindingResult, Model model,
                           RedirectAttributes ra, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
            model.addAttribute("errores", bindingResult.getAllErrors());
            model.addAttribute("areas", areasService.findAllByEstado((short) 1));
            return "componentesForm";
        }

        if (componente.getIdArea() == null){
            model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
            model.addAttribute("errores", "Selecciona un área. Si no hay disponibles crea uno primero");
            model.addAttribute("areas", areasService.findAllByEstado((short) 1));
        }

        componentesService.saveComponentes(componente);

        ra.addFlashAttribute("mensaje", "Se ha guardado con éxito");

        return "redirect:/componentes/index";
    }


    @GetMapping("edit/{id}")
    public String editArea(@PathVariable("id") Integer id, Model model, Authentication authentication) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("componente", componentesService.findById(id));
        model.addAttribute("areas", areasService.findAllByEstado((short) 1));
        return "componentesForm";
    }

    @GetMapping("delete/{id}")
    public String deleteArea(@PathVariable("id") Integer id, RedirectAttributes ra) {
        componentesService.deleteComponentes(componentesService.findById(id));
        ra.addFlashAttribute("mensaje", "Se ha eliminado con éxito");
        return "redirect:/componentes/index";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
    }
}
