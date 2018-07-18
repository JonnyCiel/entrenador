/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaweb.entrenador.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Jonny
 */
@Entity
@Table(name = "evaluacion_preguntas")
public class EvaluacionPreguntas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "evaluacion_id", referencedColumnName = "id_evaluacion")
    @ManyToOne(optional = false)
    private Evaluacion evaluacionId;
    @JoinColumn(name = "preguntas_id", referencedColumnName = "id_pregunta")
    @ManyToOne(optional = false)
    private Preguntas preguntasId;

    public EvaluacionPreguntas() {
    }

    public EvaluacionPreguntas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Evaluacion getEvaluacionId() {
        return evaluacionId;
    }

    public void setEvaluacionId(Evaluacion evaluacionId) {
        this.evaluacionId = evaluacionId;
    }

    public Preguntas getPreguntasId() {
        return preguntasId;
    }

    public void setPreguntasId(Preguntas preguntasId) {
        this.preguntasId = preguntasId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvaluacionPreguntas)) {
            return false;
        }
        EvaluacionPreguntas other = (EvaluacionPreguntas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EvaluacionPreguntas[ id=" + id + " ]";
    }
    
}
