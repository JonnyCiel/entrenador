package com.sigaweb.entrenador.entities;

public class VincularPreguntasWrapper {

    private Integer idEvaluacion;
    private Short nivel = 1;
    private Short profudizacion = 1;

    public VincularPreguntasWrapper() {
    }

    public VincularPreguntasWrapper(Integer idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public Integer getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(Integer idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public Short getNivel() {
        return nivel;
    }

    public void setNivel(Short nivel) {
        this.nivel = nivel;
    }

    public Short getProfudizacion() {
        return profudizacion;
    }

    public void setProfudizacion(Short profudizacion) {
        this.profudizacion = profudizacion;
    }
}
