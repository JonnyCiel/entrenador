package com.sigaweb.entrenador.controller;

import com.sigaweb.entrenador.entities.Areas;
import com.sigaweb.entrenador.service.AreasService;
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
@RequestMapping("areas")
public class AreasController {

    @Autowired
    private UserService userService;

    @Autowired
    private AreasService areasService;

    @GetMapping("index")
    public String indexArea(@PageableDefault(value = 10, page = 0) Pageable page, Model model,
                                    Authentication authentication) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("areas", areasService.findAllAreas(page));
        return "areasList";
    }


    @GetMapping("form")
    public String areaForm(Authentication authentication, Model model) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("area", new Areas());

        return "areasForm";
    }


    @PostMapping("save")
    public String saveForm(@Valid @ModelAttribute Areas area, BindingResult bindingResult, Model model,
                           RedirectAttributes ra, Authentication authentication) {


        if (bindingResult.hasErrors()) {
            model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
            model.addAttribute("errores", bindingResult.getAllErrors());
            return "areasForm";
        }

        areasService.saveAreas(area);

        ra.addFlashAttribute("mensaje", "Se ha guardado con éxito");

        return "redirect:/areas/index";
    }


    @GetMapping("edit/{id}")
    public String editArea(@PathVariable("id") Integer id, Model model, Authentication authentication) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("area", areasService.findById(id));
        return "areasForm";
    }

    @GetMapping("delete/{id}")
    public String deleteArea(@PathVariable("id") Integer id, RedirectAttributes ra) {
        areasService.deleteAreas(areasService.findById(id));
        ra.addFlashAttribute("mensaje", "Se ha eliminado con éxito");
        return "redirect:/areas/index";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
    }
}
