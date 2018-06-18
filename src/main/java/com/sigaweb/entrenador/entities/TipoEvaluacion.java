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
@Table(name = "tipo_evaluacion")
@NamedQueries({
    @NamedQuery(name = "TipoEvaluacion.findAll", query = "SELECT t FROM TipoEvaluacion t")})
public class TipoEvaluacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_evaluacion")
    private Integer idTipoEvaluacion;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "estado")
    private Short estado;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();
    @Basic(optional = false)
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();
    @OneToMany(mappedBy = "idTipoEvaluacion")
    private List<Evaluacion> evaluacionList;

    public TipoEvaluacion() {
    }

    public TipoEvaluacion(Integer idTipoEvaluacion) {
        this.idTipoEvaluacion = idTipoEvaluacion;
    }

    public TipoEvaluacion(Integer idTipoEvaluacion, String nombre, Date createdAt, Date updatedAt) {
        this.idTipoEvaluacion = idTipoEvaluacion;
        this.nombre = nombre;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getIdTipoEvaluacion() {
        return idTipoEvaluacion;
    }

    public void setIdTipoEvaluacion(Integer idTipoEvaluacion) {
        this.idTipoEvaluacion = idTipoEvaluacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public List<Evaluacion> getEvaluacionList() {
        return evaluacionList;
    }

    public void setEvaluacionList(List<Evaluacion> evaluacionList) {
        this.evaluacionList = evaluacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoEvaluacion != null ? idTipoEvaluacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEvaluacion)) {
            return false;
        }
        TipoEvaluacion other = (TipoEvaluacion) object;
        if ((this.idTipoEvaluacion == null && other.idTipoEvaluacion != null) || (this.idTipoEvaluacion != null && !this.idTipoEvaluacion.equals(other.idTipoEvaluacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TipoEvaluacion[ idTipoEvaluacion=" + idTipoEvaluacion + " ]";
    }
    
}
