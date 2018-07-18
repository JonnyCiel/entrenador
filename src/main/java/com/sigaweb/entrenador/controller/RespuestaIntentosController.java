package com.sigaweb.entrenador.controller;

import com.sigaweb.entrenador.entities.*;
import com.sigaweb.entrenador.repository.*;
import com.sigaweb.entrenador.service.EvaluacionService;
import com.sigaweb.entrenador.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String saveRespuestaIntento(@ModelAttribute QuizWraper quizWraper,
                                       Authentication authentication,
                                       Model model) {
        if (quizWraper.getPreguntas().size() > 1) {
            List<Preguntas> newList = quizWraper.getPreguntas();
            newList.remove(newList.get(0));
            quizWraper.setPreguntas(newList);
        }


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

        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("preguntas", quizWraper);


        return "redirect:/evaluaciones/quiz/" + quizWraper.getEvaluacion();
    }
}
