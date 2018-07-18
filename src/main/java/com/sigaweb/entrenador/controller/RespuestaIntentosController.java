package com.sigaweb.entrenador.controller;

import com.sigaweb.entrenador.entities.*;
import com.sigaweb.entrenador.repository.*;
import com.sigaweb.entrenador.service.EvaluacionService;
import com.sigaweb.entrenador.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("resint")
public class RespuestaIntentosController {

    @Autowired
    private UserService userService;

    @Autowired
    private EvaluacionService evaluacionService;


    @Autowired
    private RespuestaIntentosRepository respuestaIntentosRepository;


    @Autowired
    private IntentosRepository intentosRepository;

    @Autowired
    private PreguntasRepository preguntasRepository;

    @Autowired
    private RespuestasRepository respuestasRepository;

    @PostMapping("save")
    // @ResponseStatus(value = HttpStatus.OK)
    public String saveRespuestaIntento(@ModelAttribute QuizWraper quizWraper,
                                       Authentication authentication,
                                       Model model, RedirectAttributes ra) {

        Optional<Intentos> optionalIntentos = intentosRepository.findById(quizWraper.getIntento());
        Intentos intento = optionalIntentos.orElse(null);

        Optional<Preguntas> optionalPreguntas = preguntasRepository.findById(quizWraper.getPreguntas().get(0).getIdPregunta());
        Preguntas pregunta = optionalPreguntas.orElse(null);

        Optional<Respuesta> optionalRespuesta = respuestasRepository.findById(quizWraper.getRespuesta());
        Respuesta Respuesta = optionalRespuesta.orElse(null);

        RespuestasIntentos respuestasIntentos = new RespuestasIntentos();
        respuestasIntentos.setIntentoId(intento);
        respuestasIntentos.setPreguntaId(pregunta);
        respuestasIntentos.setRespuestaId(Respuesta);
        respuestasIntentos.setVerificacion((short) 0);
        respuestasIntentos.setTiempo(new Date());

        respuestaIntentosRepository.save(respuestasIntentos);

        List<Preguntas> newList = quizWraper.getPreguntas();

        if (quizWraper.getPreguntas().size() > 1) {
            newList.remove(newList.get(0));
            quizWraper.setPreguntas(newList);
        } else {
            ra.addFlashAttribute("mensaje", "Has terminado la evaluación");
            return "redirect:/evaluaciones/index/";
        }


        quizWraper.setPregunta(newList.get(0).getIdPregunta());

        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("preguntas", quizWraper);


//        return "redirect:/evaluaciones/quiz/" + quizWraper.getEvaluacion();
        return "quizLayout";
    }
}
