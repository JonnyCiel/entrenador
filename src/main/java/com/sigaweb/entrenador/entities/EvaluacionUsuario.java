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
@Table(name = "evaluacion_usuario")
@NamedQueries({
    @NamedQuery(name = "EvaluacionUsuario.findAll", query = "SELECT e FROM EvaluacionUsuario e")})
public class EvaluacionUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_eval_usuario")
    private Integer idEvalUsuario;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "id_evaluacion", referencedColumnName = "id_evaluacion")
    @ManyToOne
    private Evaluacion idEvaluacion;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;
    @OneToMany(mappedBy = "idEvalUsuario")
    private List<Intentos> intentosList;

    public EvaluacionUsuario() {
    }

    public EvaluacionUsuario(Integer idEvalUsuario) {
        this.idEvalUsuario = idEvalUsuario;
    }

    public EvaluacionUsuario(Integer idEvalUsuario, Date createdAt, Date updatedAt) {
        this.idEvalUsuario = idEvalUsuario;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getIdEvalUsuario() {
        return idEvalUsuario;
    }

    public void setIdEvalUsuario(Integer idEvalUsuario) {
        this.idEvalUsuario = idEvalUsuario;
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

    public Evaluacion getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(Evaluacion idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Intentos> getIntentosList() {
        return intentosList;
    }

    public void setIntentosList(List<Intentos> intentosList) {
        this.intentosList = intentosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvalUsuario != null ? idEvalUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvaluacionUsuario)) {
            return false;
        }
        EvaluacionUsuario other = (EvaluacionUsuario) object;
        if ((this.idEvalUsuario == null && other.idEvalUsuario != null) || (this.idEvalUsuario != null && !this.idEvalUsuario.equals(other.idEvalUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EvaluacionUsuario[ idEvalUsuario=" + idEvalUsuario + " ]";
    }
    
}
