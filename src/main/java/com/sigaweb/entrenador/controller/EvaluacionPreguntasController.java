package com.sigaweb.entrenador.controller;

import com.sigaweb.entrenador.entities.Evaluacion;
import com.sigaweb.entrenador.entities.EvaluacionPreguntas;
import com.sigaweb.entrenador.entities.Preguntas;
import com.sigaweb.entrenador.entities.VincularPreguntasWrapper;
import com.sigaweb.entrenador.service.EvaluacionPreguntasService;
import com.sigaweb.entrenador.service.PreguntasService;
import com.sigaweb.entrenador.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("evaluapregunta")
public class EvaluacionPreguntasController {

    @Autowired
    private UserService userService;

    @Autowired
    private PreguntasService preguntasService;

    @Autowired
    private EvaluacionPreguntasService evaluacionPreguntasService;

    @GetMapping("form/{id}")
    public String linkEvaluacionPreguntas(@PathVariable("id") Integer id, Authentication authentication, Model model) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("evaluapregunta", new VincularPreguntasWrapper(id));
        model.addAttribute("listaPreguntas", evaluacionPreguntasService.findByidEvaluacion(id));


        return "vincularpreguntasevaluacionesForm";
    }


    @PostMapping("save")
    public String saveEvaluacionPreguntas(@ModelAttribute VincularPreguntasWrapper wrapper, Model model,
                                          RedirectAttributes ra, BindingResult br) {

        List<EvaluacionPreguntas> guardadasList = evaluacionPreguntasService.findByidEvaluacion(wrapper.getIdEvaluacion());
        List<Preguntas> preguntas = preguntasService.findByNivelAndProfundizacion(wrapper.getNivel(), wrapper.getProfudizacion());
        boolean added = false;
        int sum = 0;

        if (guardadasList.isEmpty()) {
            for (Preguntas preg : preguntas) {
                EvaluacionPreguntas evaluacionPreguntas = new EvaluacionPreguntas();
                evaluacionPreguntas.setPreguntasId(preg);
                evaluacionPreguntas.setEvaluacionId(new Evaluacion(wrapper.getIdEvaluacion()));
                evaluacionPreguntasService.saveEvaluacionPreguntas(evaluacionPreguntas);
                added = true;
                sum++;
            }
        } else {
            for (int i = 0; i < preguntas.size(); i++) {
                if (!guardadasList.get(i).getPreguntasId().getIdPregunta().equals(preguntas.get(i).getIdPregunta())) {
                    EvaluacionPreguntas evaluacionPreguntas = new EvaluacionPreguntas();
                    evaluacionPreguntas.setPreguntasId(new Preguntas(preguntas.get(i).getIdPregunta()));
                    evaluacionPreguntas.setEvaluacionId(new Evaluacion(wrapper.getIdEvaluacion()));
                    evaluacionPreguntasService.saveEvaluacionPreguntas(evaluacionPreguntas);
                    added = true;
                    sum++;
                }
            }
        }


        if (added) {
            ra.addFlashAttribute("mensaje", "Se han añadido " + sum + " preguntas a la evaluación");
        } else {
            ra.addFlashAttribute("mensaje", "Ya han sido agregadas las preguntas con los criterios que has seleccionado o no existen");
        }


        return "redirect:/evaluaciones/index";
    }
}
