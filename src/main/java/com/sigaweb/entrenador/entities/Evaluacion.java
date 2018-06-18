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
@Table(name = "evaluacion")
@NamedQueries({
    @NamedQuery(name = "Evaluacion.findAll", query = "SELECT e FROM Evaluacion e")})
public class Evaluacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_evaluacion")
    private Integer idEvaluacion;
    @Basic(optional = false)
    @Column(name = "cantidad_intentos")
    private short cantidadIntentos = 1;
    @Basic(optional = false)
    @Column(name = "fecha_limite")
    private Date fechaLimite;
    @Basic(optional = false)
    @Column(name = "fecha_inicio")
    private Date fechaInicio;
    @Column(name = "estado")
    private Short estado;
    @Basic(optional = false)
    @Column(name = "created_at")
    private Date createdAt = new Date();
    @Basic(optional = false)
    @Column(name = "updated_at")
    private Date updatedAt = new Date();
    @JoinColumn(name = "id_tipo_evaluacion", referencedColumnName = "id_tipo_evaluacion")
    @ManyToOne
    private TipoEvaluacion idTipoEvaluacion;
    @OneToMany(mappedBy = "idEvaluacion")
    private List<EvaluacionUsuario> evaluacionUsuarioList;

    public Evaluacion() {
    }

    public Evaluacion(Integer idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public Evaluacion(Integer idEvaluacion, short cantidadIntentos, Date fechaLimite, Date fechaInicio, Date createdAt, Date updatedAt) {
        this.idEvaluacion = idEvaluacion;
        this.cantidadIntentos = cantidadIntentos;
        this.fechaLimite = fechaLimite;
        this.fechaInicio = fechaInicio;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(Integer idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public short getCantidadIntentos() {
        return cantidadIntentos;
    }

    public void setCantidadIntentos(short cantidadIntentos) {
        this.cantidadIntentos = cantidadIntentos;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
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

    public TipoEvaluacion getIdTipoEvaluacion() {
        return idTipoEvaluacion;
    }

    public void setIdTipoEvaluacion(TipoEvaluacion idTipoEvaluacion) {
        this.idTipoEvaluacion = idTipoEvaluacion;
    }

    public List<EvaluacionUsuario> getEvaluacionUsuarioList() {
        return evaluacionUsuarioList;
    }

    public void setEvaluacionUsuarioList(List<EvaluacionUsuario> evaluacionUsuarioList) {
        this.evaluacionUsuarioList = evaluacionUsuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvaluacion != null ? idEvaluacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluacion)) {
            return false;
        }
        Evaluacion other = (Evaluacion) object;
        if ((this.idEvaluacion == null && other.idEvaluacion != null) || (this.idEvaluacion != null && !this.idEvaluacion.equals(other.idEvaluacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Evaluacion[ idEvaluacion=" + idEvaluacion + " ]";
    }
    
}
