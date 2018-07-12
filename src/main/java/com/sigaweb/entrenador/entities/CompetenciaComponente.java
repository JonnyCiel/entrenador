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
 *
 * @author Jonny
 */
@Entity
@Table(name = "competencia_componente")
public class CompetenciaComponente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comp_componente")
    private Integer idCompComponente;
    @Column(name = "estado")
    private Short estado = 1;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "id_competencia", referencedColumnName = "id_competencia")
    @ManyToOne
    private Competencias idCompetencia;
    @JoinColumn(name = "id_componente", referencedColumnName = "id_componente")
    @ManyToOne
    private Componentes idComponente;
    @OneToMany(mappedBy = "idCompComponente")
    private List<Preguntas> preguntasList;

    public CompetenciaComponente() {
    }

    public CompetenciaComponente(Integer idCompComponente) {
        this.idCompComponente = idCompComponente;
    }

    public CompetenciaComponente(Integer idCompComponente, Date createdAt, Date updatedAt) {
        this.idCompComponente = idCompComponente;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getIdCompComponente() {
        return idCompComponente;
    }

    public void setIdCompComponente(Integer idCompComponente) {
        this.idCompComponente = idCompComponente;
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

    public Competencias getIdCompetencia() {
        return idCompetencia;
    }

    public void setIdCompetencia(Competencias idCompetencia) {
        this.idCompetencia = idCompetencia;
    }

    public Componentes getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(Componentes idComponente) {
        this.idComponente = idComponente;
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
        hash += (idCompComponente != null ? idCompComponente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompetenciaComponente)) {
            return false;
        }
        CompetenciaComponente other = (CompetenciaComponente) object;
        if ((this.idCompComponente == null && other.idCompComponente != null) || (this.idCompComponente != null && !this.idCompComponente.equals(other.idCompComponente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CompetenciaComponente[ idCompComponente=" + idCompComponente + " ]";
    }
    
}
