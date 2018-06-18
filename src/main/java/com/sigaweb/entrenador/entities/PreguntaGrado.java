/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaweb.entrenador.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jonny
 */
@Entity
@Table(name = "pregunta_grado")
@NamedQueries({
    @NamedQuery(name = "PreguntaGrado.findAll", query = "SELECT p FROM PreguntaGrado p")})
public class PreguntaGrado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_preg_grado")
    private Integer idPregGrado;
    @Column(name = "estado")
    private Short estado;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "id_grado", referencedColumnName = "id_grado")
    @ManyToOne
    private Grados idGrado;
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta")
    @ManyToOne
    private Preguntas idPregunta;

    public PreguntaGrado() {
    }

    public PreguntaGrado(Integer idPregGrado) {
        this.idPregGrado = idPregGrado;
    }

    public PreguntaGrado(Integer idPregGrado, Date createdAt, Date updatedAt) {
        this.idPregGrado = idPregGrado;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getIdPregGrado() {
        return idPregGrado;
    }

    public void setIdPregGrado(Integer idPregGrado) {
        this.idPregGrado = idPregGrado;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Grados getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Grados idGrado) {
        this.idGrado = idGrado;
    }

    public Preguntas getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Preguntas idPregunta) {
        this.idPregunta = idPregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPregGrado != null ? idPregGrado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreguntaGrado)) {
            return false;
        }
        PreguntaGrado other = (PreguntaGrado) object;
        if ((this.idPregGrado == null && other.idPregGrado != null) || (this.idPregGrado != null && !this.idPregGrado.equals(other.idPregGrado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PreguntaGrado[ idPregGrado=" + idPregGrado + " ]";
    }
    
}
