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
@Table(name = "enunciados")
@NamedQueries({
    @NamedQuery(name = "Enunciados.findAll", query = "SELECT e FROM Enunciados e")})
public class Enunciados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_enunciado")
    private Integer idEnunciado;
    @Lob
    @Column(name = "texto")
    private String texto;
    @Column(name = "estado")
    private Short estado;
    @Column(name = "distractor")
    private String distractor;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();
    @Basic(optional = false)
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();
    @OneToMany(mappedBy = "idEnunciado")
    private List<Preguntas> preguntasList;

    public Enunciados() {
    }

    public Enunciados(Integer idEnunciado) {
        this.idEnunciado = idEnunciado;
    }

    public Enunciados(Integer idEnunciado, Date createdAt, Date updatedAt) {
        this.idEnunciado = idEnunciado;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getIdEnunciado() {
        return idEnunciado;
    }

    public void setIdEnunciado(Integer idEnunciado) {
        this.idEnunciado = idEnunciado;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }

    public String getDistractor() {
        return distractor;
    }

    public void setDistractor(String distractor) {
        this.distractor = distractor;
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

    public List<Preguntas> getPreguntasList() {
        return preguntasList;
    }

    public void setPreguntasList(List<Preguntas> preguntasList) {
        this.preguntasList = preguntasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEnunciado != null ? idEnunciado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enunciados)) {
            return false;
        }
        Enunciados other = (Enunciados) object;
        if ((this.idEnunciado == null && other.idEnunciado != null) || (this.idEnunciado != null && !this.idEnunciado.equals(other.idEnunciado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Enunciados[ idEnunciado=" + idEnunciado + " ]";
    }
    
}
