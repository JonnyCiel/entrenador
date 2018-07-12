package com.sigaweb.entrenador.controller;

import com.sigaweb.entrenador.entities.TipoEvaluacion;
import com.sigaweb.entrenador.service.TipoEvaluacionService;
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
@RequestMapping("tipoevaluaciones")
public class TipoEvaluacionesController {

    @Autowired
    private UserService userService;

    @Autowired
    private TipoEvaluacionService tipoEvaluacionService;

    @GetMapping("index")
    public String indexArea(@PageableDefault(value = 10, page = 0) Pageable page, Model model,
                                    Authentication authentication) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("tipoevaluacion", tipoEvaluacionService.findAllTipoEvaluacion(page));
        return "tipoEvaluacionList";
    }


    @GetMapping("form")
    public String areaForm(Authentication authentication, Model model) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("tipoevaluacion", new TipoEvaluacion());

        return "tipoEvaluacionForm";
    }


    @PostMapping("save")
    public String saveForm(@Valid @ModelAttribute TipoEvaluacion tipoEvaluacion, BindingResult bindingResult, Model model,
                           RedirectAttributes ra, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
            model.addAttribute("errores", bindingResult.getAllErrors());
            return "tipoEvaluacionForm";
        }

        tipoEvaluacionService.saveTipoEvaluacion(tipoEvaluacion);

        ra.addFlashAttribute("mensaje", "Se ha guardado con éxito");

        return "redirect:/tipoevaluaciones/index";
    }


    @GetMapping("edit/{id}")
    public String editArea(@PathVariable("id") Integer id, Model model, Authentication authentication) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("tipoevaluacion", tipoEvaluacionService.findById(id));
        return "tipoEvaluacionForm";
    }

    @GetMapping("delete/{id}")
    public String deleteArea(@PathVariable("id") Integer id, RedirectAttributes ra) {
        tipoEvaluacionService.deleteTipoEvaluacion(tipoEvaluacionService.findById(id));
        ra.addFlashAttribute("mensaje", "Se ha eliminado con éxito");
        return "redirect:/tipoevaluaciones/index";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
    }
}
