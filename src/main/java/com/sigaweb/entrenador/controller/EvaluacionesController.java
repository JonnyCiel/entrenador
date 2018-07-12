package com.sigaweb.entrenador.controller;

import com.sigaweb.entrenador.entities.Evaluacion;
import com.sigaweb.entrenador.service.EvaluacionService;
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
@RequestMapping("evaluaciones")
public class EvaluacionesController {

    @Autowired
    private UserService userService;

    @Autowired
    private EvaluacionService evaluacionService;

    @Autowired
    private TipoEvaluacionService tipoEvaluacionService;

    @GetMapping("index")
    public String indexEvaluaciones(@PageableDefault(value = 10, page = 0) Pageable page, Model model,
                                    Authentication authentication) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("evaluaciones", evaluacionService.findAllEvaluaciones(page));
        return "evaluacionesList";
    }

    @GetMapping("form")
    public String formEvaluaciones(Model model, Authentication authentication) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("evaluacion", new Evaluacion());
        model.addAttribute("tipoevaluacion", tipoEvaluacionService.findAllByEstado((short) 1));

        return "evaluacionForm";
    }


    @PostMapping("save")
    public String saveEvaluciones(@Valid @ModelAttribute Evaluacion evaluacion, BindingResult bindingResult, Model model,
                                  RedirectAttributes ra, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
            model.addAttribute("errores", bindingResult.getAllErrors());
            model.addAttribute("tipoevaluacion", tipoEvaluacionService.findAllByEstado((short) 1));
            return "evaluacionForm";
        }

        if (evaluacion.getFechaLimite().before(evaluacion.getFechaInicio())) {
            model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
            model.addAttribute("errores", "La fecha final no debe ser inferior a la de inicio");
            model.addAttribute("tipoevaluacion", tipoEvaluacionService.findAllByEstado((short) 1));
            return "evaluacionForm";
        }

        if(evaluacion.getIdTipoEvaluacion() == null){
            model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
            model.addAttribute("errores", "Selecciona un tipo de evaluación. Si no hay disponibles crea uno primero.");
            model.addAttribute("tipoevaluacion", tipoEvaluacionService.findAllByEstado((short) 1));
            return "evaluacionForm";
        }

        evaluacionService.saveEvaluacion(evaluacion);

        ra.addFlashAttribute("mensaje", "Se ha guardado con éxito");

        return "redirect:/evaluaciones/index";
    }


    @GetMapping("edit/{id}")
    public String editEvaluaciones(@PathVariable("id") Integer id, Authentication authentication, Model model) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("tipoevaluacion", tipoEvaluacionService.findAllByEstado((short) 1));
        model.addAttribute("evaluacion", evaluacionService.findById(id));

        return "evaluacionForm";

    }


    @GetMapping("delete/{id}")
    public String deleteEvaluaciones(@PathVariable("id") Integer id, RedirectAttributes ra) {

        evaluacionService.deleteEvaluacion(evaluacionService.findById(id));
        ra.addFlashAttribute("mensaje", "Se ha eliminado con éxito");

        return "redirect:/evaluaciones/index";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
    }

}
