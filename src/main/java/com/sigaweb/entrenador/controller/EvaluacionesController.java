package com.sigaweb.entrenador.controller;

import com.sigaweb.entrenador.entities.*;
import com.sigaweb.entrenador.repository.EvaluacionUsuarioRepository;
import com.sigaweb.entrenador.repository.IntentosRepository;
import com.sigaweb.entrenador.service.*;
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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("evaluaciones")
public class EvaluacionesController {

    @Autowired
    private UserService userService;

    @Autowired
    private EvaluacionService evaluacionService;

    @Autowired
    private TipoEvaluacionService tipoEvaluacionService;

    @Autowired
    private EvaluacionPreguntasService evaluacionPreguntasService;

    @Autowired
    private PreguntasService preguntasService;

    @Autowired
    private EvaluacionUsuarioRepository evaluacionUsuarioRepository;

    @Autowired
    private IntentosRepository intentosRepository;


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


        System.out.println("hora local: " + LocalDateTime.now(ZoneId.systemDefault()).getHour());

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

        if (evaluacion.getIdTipoEvaluacion() == null) {
            model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
            model.addAttribute("errores", "Selecciona un tipo de evaluación. Si no hay disponibles crea uno primero.");
            model.addAttribute("tipoevaluacion", tipoEvaluacionService.findAllByEstado((short) 1));
            return "evaluacionForm";
        }

        evaluacion.setOwner(authentication.getName());

        evaluacionService.saveEvaluacion(evaluacion);

        Usuario usuario = userService.findByEmail(authentication.getName());
        EvaluacionUsuario evaluacionUsuario = new EvaluacionUsuario();
        evaluacionUsuario.setIdEvaluacion(evaluacion);
        evaluacionUsuario.setIdUsuario(usuario);

        evaluacionUsuarioRepository.save(evaluacionUsuario);

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

    @GetMapping("quiz/{id}")
    public String startEvaluaciones(@PathVariable("id") Integer id, Authentication authentication, Model model, RedirectAttributes ra) {
        if (evaluacionPreguntasService.findByidEvaluacion(id).isEmpty()) {
            ra.addFlashAttribute("mensaje", "La evaluación que intentas tomar no contiene preguntas");
            return "redirect:/evaluaciones/index";
        }


        Usuario usuario = userService.findByEmail(authentication.getName());
        Evaluacion evaluacion = evaluacionService.findById(id);
        EvaluacionUsuario evaluacionUsuario = evaluacionUsuarioRepository.findByIdUsuarioIdUsuarioAndIdEvaluacionIdEvaluacion(usuario.getIdUsuario(), id);
        Optional<Intentos> optionalIntentos = intentosRepository.findByIdEvalUsuarioIdEvalUsuarioEquals(evaluacionUsuario.getIdEvalUsuario());
        Intentos intento = null;
        if (optionalIntentos.isPresent()) {
            intento = optionalIntentos.get();
            if (intento.getNumeroIntento() >= evaluacion.getCantidadIntentos()) {
                ra.addFlashAttribute("mensaje", "Has superado la cantidad máxima de intentos");
                return "redirect:/evaluaciones/index";
            } else {
                short trys = (short) (intento.getNumeroIntento() + 1);
                intento.setIdEvalUsuario(evaluacionUsuario);
                intento.setCreatedAt(new Date());
                intento.setEstado((short) 1);
                intento.setNumeroIntento(trys);
                intento.setFechaInicio(new Date());
                intento.setFechaFin(new Date());
                intento.setUpdatedAt(new Date());
                intento.setTiempo(new Date());
                intentosRepository.save(intento);
                model.addAttribute("intentoid", intento.getIdIntento());
            }
        } else {
            intento = new Intentos();
            intento.setIdEvalUsuario(evaluacionUsuario);
            intento.setCreatedAt(new Date());
            intento.setEstado((short) 1);
            intento.setNumeroIntento((short) 1);
            intento.setFechaInicio(new Date());
            intento.setFechaFin(new Date());
            intento.setUpdatedAt(new Date());
            intento.setTiempo(new Date());
            intentosRepository.save(intento);
            model.addAttribute("intentoid", intento.getIdIntento());
        }


        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));


        List<Preguntas> preguntas = new ArrayList<>();
        List<EvaluacionPreguntas> evaluacionPreguntas = evaluacionPreguntasService.findByidEvaluacion(id);

        for (EvaluacionPreguntas data : evaluacionPreguntas) {
            preguntas.add(preguntasService.findById(data.getPreguntasId().getIdPregunta()));
        }

        QuizWraper quizWraper = new QuizWraper();
        quizWraper.setPreguntas(preguntas);
        quizWraper.setEvaluacion(id);
        quizWraper.setIntento(intento.getIdIntento());
        quizWraper.setPregunta(preguntas.get(0).getIdPregunta());
        model.addAttribute("preguntas", quizWraper);
//        model.addAttribute("preguntas", preguntas);
        //  model.addAttribute("evaluacion", evaluacionService.findById(id));


        return "quizLayout";
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

//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//        SimpleDateFormat sdfTime = new SimpleDateFormat("H:mm");
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
//        binder.registerCustomEditor(Date.class, "horaInicio", new CustomDateEditor(sdfTime, false));
//        binder.registerCustomEditor(Date.class, "horaFinal", new CustomDateEditor(sdfTime, false));
//    }

}
