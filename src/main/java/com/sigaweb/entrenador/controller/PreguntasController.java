package com.sigaweb.entrenador.controller;

import com.sigaweb.entrenador.entities.Enunciados;
import com.sigaweb.entrenador.entities.Preguntas;
import com.sigaweb.entrenador.entities.Respuesta;
import com.sigaweb.entrenador.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;


import javax.validation.Valid;

@Controller
@RequestMapping("preguntas")
public class PreguntasController {

    @Autowired
    private UserService userService;

    @Autowired
    private CompetenciaComponenteService competenciaComponenteService;

    @Autowired
    private TipoPreguntasService tipoPreguntasService;

    @Autowired
    private PreguntasService preguntasService;

    @Autowired
    private EnunciadoService enunciadoService;

    @Autowired
    private RespuestasService respuestasService;


    @GetMapping("form")
    public String getForm(Model model, Authentication authentication) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("pregunta", new Preguntas());
        model.addAttribute("tipopregunta", tipoPreguntasService.findAllByEstado((short) 1));
        model.addAttribute("competenciacomponente", competenciaComponenteService.findAllByEstado((short) 1));

        return "preguntasForm";
    }

    @GetMapping("index")
    public String indexPreguntas(@PageableDefault(value = 10, page = 0) Pageable page, Model model,
                                 Authentication authentication) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("preguntas", preguntasService.findAllPregunta(page));

        return "preguntasList";
    }


    @GetMapping("modalenunciado/{id}")
    public String modalEnunciado(@PathVariable("id") Integer id, Model model) {
//        try {
//            try {
//                URL url = new URL("http://www.google.com");
//                System.out.println(url.getHost());
//                HttpURLConnection con = (HttpURLConnection) url
//                        .openConnection();
//                con.connect();
//                if (con.getResponseCode() == 200) {
//                    System.out.println("Connection established!!");
//                    Preguntas pregunta = preguntasService.findById(id);
//                    model.addAttribute("enunciado", enunciadoService.findById(pregunta.getIdEnunciado().getIdEnunciado()));
//                }
//            } catch (Exception exception) {
//                System.out.println("No Connection");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        model.addAttribute("enunciado", enunciadoService.findById(id));
        return "enunciadomodal::enunciadomodal";
    }

    @GetMapping("delete/{id}")
    public String deletePregunta(@PathVariable("id") Integer id) {
        Preguntas pregunta = preguntasService.findById(id);
        Enunciados enunciado = enunciadoService.findById(pregunta.getIdEnunciado().getIdEnunciado());

        List<Respuesta> respuestaList = respuestasService.findAllByIdPreguntaEquals(id);
        for (int i = 0; i < respuestaList.size(); i++) {
            respuestasService.deleteRespuesta(respuestaList.get(i));
        }

        preguntasService.deletePregunta(pregunta);
        enunciadoService.deleteEnunciados(enunciado);

        return "redirect:/preguntas/index";
    }


    @GetMapping("edit/{id}")
    private String editPregunta(@PathVariable("id") Integer id, Model model, Authentication authentication) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("tipopregunta", tipoPreguntasService.findAllByEstado((short) 1));
        model.addAttribute("competenciacomponente", competenciaComponenteService.findAllByEstado((short) 1));
        model.addAttribute("pregunta", preguntasService.findById(id));
        return "preguntasForm";
    }


    @PostMapping("save")
    public String savePregunta(@Valid @ModelAttribute Preguntas pregunta, BindingResult bindingResult, Model model,
                               RedirectAttributes ra, Authentication authentication) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
            model.addAttribute("errores", bindingResult.getAllErrors());
            return "preguntasForm";
        }

        if (pregunta.getIdEnunciado().getTexto().isEmpty() && pregunta.getIdEnunciado().getDistractor().isEmpty()) {
            pregunta.getIdEnunciado().setEstado((short) 2);
        }

        if (pregunta.getIdTipoPregunta() == null) {
            model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
            formError("Elige un tipo de pregunta. Si no hay, crea uno primero.", model, pregunta);
            return "preguntasForm";
        }else if (pregunta.getIdCompComponente() == null){
            model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
            formError("Elige una competencia y componente. Si no hay, crea una primero.", model, pregunta);
            return "preguntasForm";
        }

        enunciadoService.saveEnunciados(pregunta.getIdEnunciado());
        pregunta.setIdEnunciado(pregunta.getIdEnunciado());
        preguntasService.savePregunta(pregunta);

        ra.addFlashAttribute("mensaje", "Se ha guardado con éxito");

        return "redirect:/preguntas/index";
    }

    private void formError(String mensaje, Model model, Preguntas pregunta){
        model.addAttribute("pregunta", pregunta);
        model.addAttribute("errores", mensaje);
        model.addAttribute("tipopregunta", tipoPreguntasService.findAllByEstado((short) 1));
        model.addAttribute("competenciacomponente", competenciaComponenteService.findAllByEstado((short) 1));
    }

}
