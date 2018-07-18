package com.sigaweb.entrenador.entities;

import java.util.List;

public class QuizWraper {

    private List<Preguntas> preguntas;
    private Integer respuesta;
    private Integer intento;
    private Integer pregunta;
    private Integer evaluacion;

    public Integer getIntento() {
        return intento;
    }

    public void setIntento(Integer intento) {
        this.intento = intento;
    }



    public List<Preguntas> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Preguntas> preguntas) {
        this.preguntas = preguntas;
    }

    public Integer getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Integer respuesta) {
        this.respuesta = respuesta;
    }

    public Integer getPregunta() {
        return pregunta;
    }

    public void setPregunta(Integer pregunta) {
        this.pregunta = pregunta;
    }

    public Integer getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Integer evaluacion) {
        this.evaluacion = evaluacion;
    }
}
