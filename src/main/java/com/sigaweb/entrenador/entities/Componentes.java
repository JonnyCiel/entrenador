/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaweb.entrenador.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * @author Jonny
 */
@Entity
@Table(name = "componentes")
@NamedQueries({
        @NamedQuery(name = "Componentes.findAll", query = "SELECT c FROM Componentes c")})
public class Componentes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_componente")
    private Integer idComponente;
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
    @OneToMany(mappedBy = "idComponente")
    private List<CompetenciaComponente> competenciaComponenteList;
    @JoinColumn(name = "id_area", referencedColumnName = "id_area")
    @ManyToOne
    private Areas idArea;

    public Componentes() {
    }

    public Componentes(Integer idComponente) {
        this.idComponente = idComponente;
    }

    public Componentes(Integer idComponente, String nombre, String descripcion, Date createdAt, Date updatedAt) {
        this.idComponente = idComponente;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(Integer idComponente) {
        this.idComponente = idComponente;
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

    public Areas getIdArea() {
        return idArea;
    }

    public void setIdArea(Areas idArea) {
        this.idArea = idArea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComponente != null ? idComponente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Componentes)) {
            return false;
        }
        Componentes other = (Componentes) object;
        if ((this.idComponente == null && other.idComponente != null) || (this.idComponente != null && !this.idComponente.equals(other.idComponente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Componentes[ idComponente=" + idComponente + " ]";
    }

}
