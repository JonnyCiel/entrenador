package com.sigaweb.entrenador.controller;

import com.sigaweb.entrenador.entities.Areas;
import com.sigaweb.entrenador.entities.Grados;
import com.sigaweb.entrenador.entities.PreguntaGrado;
import com.sigaweb.entrenador.entities.Preguntas;
import com.sigaweb.entrenador.repository.PreguntasSearch;
import com.sigaweb.entrenador.service.GradoService;
import com.sigaweb.entrenador.service.PreguntasService;
import com.sigaweb.entrenador.service.RelacionGradoService;
import com.sigaweb.entrenador.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("regradopregunta")
public class RelacionGradoPreguntaController {


    @Autowired
    private UserService userService;

    @Autowired
    private RelacionGradoService relacionGradoService;

    @Autowired
    private GradoService gradoService;

    @Autowired
    private PreguntasSearch searchservice;

    @Autowired
    private PreguntasService preguntasService;

    @GetMapping("index")
    public String indexRelaciongGradoPregunta(@PageableDefault(value = 10, page = 0) Pageable page, Model model,
                                              Authentication authentication) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("relaciones", relacionGradoService.findAllRelacionGrado(page));
        return "relacionPreguntasList";
    }


    @GetMapping("form")
    public String areaForm(Authentication authentication, Model model) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("grados", gradoService.findAll());
        model.addAttribute("relacion", new PreguntaGrado());
        return "relacionPreguntasForm";
    }


    @GetMapping("buscar")
    public String search(@RequestParam(value = "texto", required = false) String q,
                         Model model, Authentication authentication) {

        Preguntas preguntaResults = null;


        try {
            preguntaResults = searchservice.search(q);
        } catch (Exception ex) {
            // here you should handle unexpected errors
            // ...
            // throw ex;
        }
        if (preguntaResults == null) {
            model.addAttribute("errores", "No se han encontrado coincidencias");
            model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
            model.addAttribute("grados", gradoService.findAll());
            model.addAttribute("relacion", new PreguntaGrado());

            return "relacionPreguntasForm";
        }

        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("relacion", new PreguntaGrado());
        model.addAttribute("search", preguntaResults);
        model.addAttribute("grados", gradoService.findAll());
        model.addAttribute("relaciooones", relacionGradoService.findAllByIdPregunta(preguntaResults.getIdPregunta()));

        return "relacionPreguntasForm";
    }

    @PostMapping("save")
    public String save(@RequestParam(value = "texto", required = false) String q, @Valid @ModelAttribute PreguntaGrado preguntaGrado, BindingResult bindingResult, Model model,
                       RedirectAttributes ra, Authentication authentication) {

        preguntaGrado.setIdPregunta(preguntasService.findByName(q));
        List<PreguntaGrado> relacioones = relacionGradoService.findAllByIdPregunta(preguntasService.findByName(q).getIdPregunta());

        for (PreguntaGrado relacioone : relacioones) {
            if (relacioone.getIdGrado().getIdGrado().equals(preguntaGrado.getIdGrado().getIdGrado())) {
                model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
                model.addAttribute("errores", "Esta relación ya existe, elige otro grado");
                model.addAttribute("grados", gradoService.findAll());
                model.addAttribute("relacion", preguntaGrado);
                model.addAttribute("search", preguntasService.findByName(q));
                model.addAttribute("relaciooones", relacionGradoService.findAllByIdPregunta(preguntasService.findByName(q).getIdPregunta()));
                return "relacionPreguntasForm";
            }
        }


        if (bindingResult.hasErrors()) {
            model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
            model.addAttribute("errores", bindingResult.getAllErrors());
            model.addAttribute("grados", gradoService.findAll());
            model.addAttribute("relacion", preguntaGrado);
            model.addAttribute("relaciooones", relacionGradoService.findAllByIdPregunta(preguntasService.findByName(q).getIdPregunta()));
            return "relacionPreguntasForm";
        }

        if (preguntaGrado.getIdPregunta() == null) {
            model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
            model.addAttribute("errores", "No buscaste ninguna pregunta");
            model.addAttribute("grados", gradoService.findAll());
            model.addAttribute("relacion", preguntaGrado);
            model.addAttribute("relaciooones", relacionGradoService.findAllByIdPregunta(preguntasService.findByName(q).getIdPregunta()));
            return "relacionPreguntasForm";
        }

        relacionGradoService.saveRelacionGrado(preguntaGrado);

        ra.addFlashAttribute("mensaje", "Se ha guardado con éxito la relación");
        return "redirect:/regradopregunta/index";
    }


    @GetMapping("formRe/{id}")
    public String relaForm(@PathVariable("id") Integer id, Authentication authentication, Model model) {
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("grados", gradoService.findAll());
        model.addAttribute("pregunta", preguntasService.findById(id));
        model.addAttribute("relacion", new PreguntaGrado());
        model.addAttribute("pregID", id);
        model.addAttribute("relaciooones",relacionGradoService.findAllByIdPregunta(id));


        return "relacionPreguntasFormFromList";
    }

    @PostMapping("saveFromList/{id}")
    public String saveFromList(@PathVariable("id") Integer id, @Valid @ModelAttribute PreguntaGrado preguntaGrado,  Model model,
                               RedirectAttributes ra, Authentication authentication){

        System.out.println(id);
        preguntaGrado.setIdPregunta(preguntasService.findById(id));
        List<PreguntaGrado> relacioones = relacionGradoService.findAllByIdPregunta(id);

        for (PreguntaGrado relacioone : relacioones) {
            if (relacioone.getIdGrado().getIdGrado().equals(preguntaGrado.getIdGrado().getIdGrado())) {
                model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
                model.addAttribute("errores", "Esta relación ya existe, elige otro grado");
                model.addAttribute("grados", gradoService.findAll());
                model.addAttribute("relacion", preguntaGrado);
                model.addAttribute("pregID", id);
                model.addAttribute("pregunta", preguntaGrado.getIdPregunta());
                model.addAttribute("relaciooones",relacionGradoService.findAllByIdPregunta(id));

                return "relacionPreguntasFormFromList";
            }
        }

        relacionGradoService.saveRelacionGrado(preguntaGrado);

        ra.addFlashAttribute("mensaje", "Se ha guardado con éxito la relación");
        return "redirect:/regradopregunta/index";
    }


    @GetMapping("delete/{id}")
    public String deleteArea(@PathVariable("id") Integer id, RedirectAttributes ra) {
        relacionGradoService.deleteRelacionGrado(relacionGradoService.findById(id));
        ra.addFlashAttribute("mensaje", "Se ha eliminado con éxito");
        return "redirect:/regradopregunta/index";
    }

    @GetMapping("edit/{id}")
    public String editArea(@PathVariable("id") Integer id, Model model, Authentication authentication) {
        PreguntaGrado relacion = relacionGradoService.findById(id);
        model.addAttribute("relacion", relacionGradoService.findById(id));
        model.addAttribute("usuario", userService.findByEmail(authentication.getName()));
        model.addAttribute("search", relacion.getIdPregunta());
        model.addAttribute("grados", gradoService.findAll());
        model.addAttribute("relaciooones", relacionGradoService.findAllByIdPregunta(relacion.getIdPregunta().getIdPregunta()));
        return "relacionPreguntasForm";
    }
}
