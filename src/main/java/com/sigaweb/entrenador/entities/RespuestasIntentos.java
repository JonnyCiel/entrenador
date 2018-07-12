/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaweb.entrenador.entities;;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "respuestas_intentos")
public class RespuestasIntentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_resp_intentos")
    private Integer idRespIntentos;
    @Column(name = "verificacion")
    private Short verificacion;
    @Column(name = "tiempo")
    @Temporal(TemporalType.TIME)
    private Date tiempo;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "intento_id", referencedColumnName = "id_intento")
    @ManyToOne
    private Intentos intentoId;
    @JoinColumn(name = "pregunta_id", referencedColumnName = "id_pregunta")
    @ManyToOne
    private Preguntas preguntaId;
    @JoinColumn(name = "respuesta_id", referencedColumnName = "id_respuesta")
    @ManyToOne
    private Respuesta respuestaId;

    public RespuestasIntentos() {
    }

    public RespuestasIntentos(Integer idRespIntentos) {
        this.idRespIntentos = idRespIntentos;
    }

    public RespuestasIntentos(Integer idRespIntentos, Date createdAt, Date updatedAt) {
        this.idRespIntentos = idRespIntentos;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getIdRespIntentos() {
        return idRespIntentos;
    }

    public void setIdRespIntentos(Integer idRespIntentos) {
        this.idRespIntentos = idRespIntentos;
    }

    public Short getVerificacion() {
        return verificacion;
    }

    public void setVerificacion(Short verificacion) {
        this.verificacion = verificacion;
    }

    public Date getTiempo() {
        return tiempo;
    }

    public void setTiempo(Date tiempo) {
        this.tiempo = tiempo;
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

    public Intentos getIntentoId() {
        return intentoId;
    }

    public void setIntentoId(Intentos intentoId) {
        this.intentoId = intentoId;
    }

    public Preguntas getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(Preguntas preguntaId) {
        this.preguntaId = preguntaId;
    }

    public Respuesta getRespuestaId() {
        return respuestaId;
    }

    public void setRespuestaId(Respuesta respuestaId) {
        this.respuestaId = respuestaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRespIntentos != null ? idRespIntentos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestasIntentos)) {
            return false;
        }
        RespuestasIntentos other = (RespuestasIntentos) object;
        if ((this.idRespIntentos == null && other.idRespIntentos != null) || (this.idRespIntentos != null && !this.idRespIntentos.equals(other.idRespIntentos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RespuestasIntentos[ idRespIntentos=" + idRespIntentos + " ]";
    }
    
}
