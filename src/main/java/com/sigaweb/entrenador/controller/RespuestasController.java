package com.sigaweb.entrenador.controller;

import com.sigaweb.entrenador.entities.Areas;
import com.sigaweb.entrenador.entities.Preguntas;
import com.sigaweb.entrenador.entities.Respuesta;
import com.sigaweb.entrenador.entities.RespuestasWraper;
import com.sigaweb.entrenador.service.AreasService;
import com.sigaweb.entrenador.service.PreguntasService;
import com.sigaweb.entrenador.service.RespuestasService;
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
import java.util.List;

@Controller
@RequestMapping("respuestas")
public class RespuestasController {

    @Autowired
    private UserService userService;

    @Autowired
    private RespuestasService respuestasService;

    @Autowired
    private PreguntasService preguntasService;


    @GetMapping("form/{id}")
    public String areaForm(@PathVariable("id") Integer id, Authentication authentication, Model model) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        List<Respuesta> respuestaList = respuestasService.findAllByIdPreguntaEquals(id);
        RespuestasWraper respuestasWraper = new RespuestasWraper();


        if (respuestaList.isEmpty()) {
            Respuesta respuesta = new Respuesta();
            respuesta.setIdPregunta(preguntasService.findById(id));

            Respuesta respuesta2 = new Respuesta();
            respuesta2.setIdPregunta(preguntasService.findById(id));

            Respuesta respuesta3 = new Respuesta();
            respuesta3.setIdPregunta(preguntasService.findById(id));

            Respuesta respuesta4 = new Respuesta();
            respuesta4.setIdPregunta(preguntasService.findById(id));

            respuestaList.add(respuesta);
            respuestaList.add(respuesta2);
            respuestaList.add(respuesta3);
            respuestaList.add(respuesta4);
        }

        respuestasWraper.setRespuestaList(respuestaList);
        model.addAttribute("respuestas", respuestasWraper);


        return "respuestasForm";
    }


    @PostMapping("save")
    public String saveForm(@ModelAttribute RespuestasWraper respuesta, BindingResult bindingResult, Model model,
                           RedirectAttributes ra, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
            model.addAttribute("errores", bindingResult.getAllErrors());
            return "respuestasForm";
        }


        List<Respuesta> respuestaList = respuesta.getRespuestaList();

        for (Respuesta show : respuestaList) {
            System.out.println(show.getCorrecta());
        }
        Short correcta = 0;
        for (Respuesta res : respuestaList) {
            if (res.getCorrecta() == null) {
                res.setCorrecta((short) 1);
            } else {
                if (res.getCorrecta() == 2) {
                    correcta++;
                }
            }

        }

        for (Respuesta aRespuestaList : respuestaList) {

            if (correcta > 1) {
                RespuestasWraper wraper = new RespuestasWraper();
                wraper.setRespuestaList(respuestaList);
                model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
                model.addAttribute("errores", "Por favor marca una sola respuesta correcta");
                model.addAttribute("respuestas", wraper);
                return "respuestasForm";
            } else if (correcta <= 0) {
                RespuestasWraper wraper = new RespuestasWraper();
                wraper.setRespuestaList(respuestaList);
                model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
                model.addAttribute("errores", "Por favor marca una respuesta como correcta");
                model.addAttribute("respuestas", wraper);
                return "respuestasForm";
            }
            respuestasService.saveRespuesta(aRespuestaList);
        }

        ra.addFlashAttribute("mensaje", "Se ha guardado con éxito");

        return "redirect:/preguntas/index";
    }


//    @GetMapping("edit/{id}")
//    public String editArea(@PathVariable("id") Integer id, Model model, Authentication authentication) {
//        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
//        model.addAttribute("area", areasService.findById(id));
//        return "areasForm";
//    }
//
//    @GetMapping("delete/{id}")
//    public String deleteArea(@PathVariable("id") Integer id, RedirectAttributes ra) {
//        areasService.deleteAreas(areasService.findById(id));
//        ra.addFlashAttribute("mensaje", "Se ha eliminado con éxito");
//        return "redirect:/areas/index";
//    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
    }
}
