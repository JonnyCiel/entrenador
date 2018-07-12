/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaweb.entrenador.entities;

import com.sigaweb.entrenador.entities.Usuario;

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
@Table(name = "me_renovacionmatricula")
public class MeRenovacionmatricula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_renovacion")
    private Integer idRenovacion;
    @Column(name = "estado")
    private Boolean estado;
    @Basic(optional = false)
    @Column(name = "fecha_creado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreado;
    @Basic(optional = false)
    @Column(name = "fecha_modificado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificado;
    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso")
    @ManyToOne
    private NeCurso idCurso;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;

    public MeRenovacionmatricula() {
    }

    public MeRenovacionmatricula(Integer idRenovacion) {
        this.idRenovacion = idRenovacion;
    }

    public MeRenovacionmatricula(Integer idRenovacion, Date fechaCreado, Date fechaModificado) {
        this.idRenovacion = idRenovacion;
        this.fechaCreado = fechaCreado;
        this.fechaModificado = fechaModificado;
    }

    public Integer getIdRenovacion() {
        return idRenovacion;
    }

    public void setIdRenovacion(Integer idRenovacion) {
        this.idRenovacion = idRenovacion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Date getFechaCreado() {
        return fechaCreado;
    }

    public void setFechaCreado(Date fechaCreado) {
        this.fechaCreado = fechaCreado;
    }

    public Date getFechaModificado() {
        return fechaModificado;
    }

    public void setFechaModificado(Date fechaModificado) {
        this.fechaModificado = fechaModificado;
    }

    public NeCurso getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(NeCurso idCurso) {
        this.idCurso = idCurso;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRenovacion != null ? idRenovacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MeRenovacionmatricula)) {
            return false;
        }
        MeRenovacionmatricula other = (MeRenovacionmatricula) object;
        if ((this.idRenovacion == null && other.idRenovacion != null) || (this.idRenovacion != null && !this.idRenovacion.equals(other.idRenovacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MeRenovacionmatricula[ idRenovacion=" + idRenovacion + " ]";
    }
    
}
