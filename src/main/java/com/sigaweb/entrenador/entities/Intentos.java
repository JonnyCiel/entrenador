/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaweb.entrenador.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jonny
 */
@Entity
@Table(name = "intentos")
@NamedQueries({
    @NamedQuery(name = "Intentos.findAll", query = "SELECT i FROM Intentos i")})
public class Intentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_intento")
    private Integer idIntento;
    @Column(name = "numero_intento")
    private Short numeroIntento;
    @Basic(optional = false)
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Basic(optional = false)
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Basic(optional = false)
    @Column(name = "tiempo")
    @Temporal(TemporalType.TIME)
    private Date tiempo;
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
    @OneToMany(mappedBy = "intentoId")
    private List<RespuestasIntentos> respuestasIntentosList;
    @JoinColumn(name = "id_eval_usuario", referencedColumnName = "id_eval_usuario")
    @ManyToOne
    private EvaluacionUsuario idEvalUsuario;

    public Intentos() {
    }

    public Intentos(Integer idIntento) {
        this.idIntento = idIntento;
    }

    public Intentos(Integer idIntento, Date fechaInicio, Date fechaFin, Date tiempo, Date createdAt, Date updatedAt) {
        this.idIntento = idIntento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tiempo = tiempo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getIdIntento() {
        return idIntento;
    }

    public void setIdIntento(Integer idIntento) {
        this.idIntento = idIntento;
    }

    public Short getNumeroIntento() {
        return numeroIntento;
    }

    public void setNumeroIntento(Short numeroIntento) {
        this.numeroIntento = numeroIntento;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getTiempo() {
        return tiempo;
    }

    public void setTiempo(Date tiempo) {
        this.tiempo = tiempo;
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

    public List<RespuestasIntentos> getRespuestasIntentosList() {
        return respuestasIntentosList;
    }

    public void setRespuestasIntentosList(List<RespuestasIntentos> respuestasIntentosList) {
        this.respuestasIntentosList = respuestasIntentosList;
    }

    public EvaluacionUsuario getIdEvalUsuario() {
        return idEvalUsuario;
    }

    public void setIdEvalUsuario(EvaluacionUsuario idEvalUsuario) {
        this.idEvalUsuario = idEvalUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIntento != null ? idIntento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Intentos)) {
            return false;
        }
        Intentos other = (Intentos) object;
        if ((this.idIntento == null && other.idIntento != null) || (this.idIntento != null && !this.idIntento.equals(other.idIntento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Intentos[ idIntento=" + idIntento + " ]";
    }
    
}
