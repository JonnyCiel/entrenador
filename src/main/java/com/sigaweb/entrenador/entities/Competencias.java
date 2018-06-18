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
import javax.persistence.Lob;
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
@Table(name = "competencias")
@NamedQueries({
    @NamedQuery(name = "Competencias.findAll", query = "SELECT c FROM Competencias c")})
public class Competencias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_competencia")
    private Integer idCompetencia;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
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
    @OneToMany(mappedBy = "idCompetencia")
    private List<CompetenciaComponente> competenciaComponenteList;

    public Competencias() {
    }

    public Competencias(Integer idCompetencia) {
        this.idCompetencia = idCompetencia;
    }

    public Competencias(Integer idCompetencia, String nombre, String descripcion, Date createdAt, Date updatedAt) {
        this.idCompetencia = idCompetencia;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getIdCompetencia() {
        return idCompetencia;
    }

    public void setIdCompetencia(Integer idCompetencia) {
        this.idCompetencia = idCompetencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public List<CompetenciaComponente> getCompetenciaComponenteList() {
        return competenciaComponenteList;
    }

    public void setCompetenciaComponenteList(List<CompetenciaComponente> competenciaComponenteList) {
        this.competenciaComponenteList = competenciaComponenteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCompetencia != null ? idCompetencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competencias)) {
            return false;
        }
        Competencias other = (Competencias) object;
        if ((this.idCompetencia == null && other.idCompetencia != null) || (this.idCompetencia != null && !this.idCompetencia.equals(other.idCompetencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Competencias[ idCompetencia=" + idCompetencia + " ]";
    }
    
}
