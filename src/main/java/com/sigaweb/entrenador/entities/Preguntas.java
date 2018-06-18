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
import javax.persistence.Lob;
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
@Table(name = "preguntas")
@NamedQueries({
    @NamedQuery(name = "Preguntas.findAll", query = "SELECT p FROM Preguntas p")})
public class Preguntas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pregunta")
    private Integer idPregunta;
    @Basic(optional = false)
    @Lob
    @Column(name = "texto")
    private String texto;
    @Column(name = "nivel")
    private Short nivel;
    @Column(name = "profudizacion")
    private Short profudizacion;
    @Column(name = "privado")
    private Short privado;
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
    @OneToMany(mappedBy = "preguntaId")
    private List<RespuestasIntentos> respuestasIntentosList;
    @OneToMany(mappedBy = "idPregunta")
    private List<PreguntaGrado> preguntaGradoList;
    @JoinColumn(name = "id_comp_componente", referencedColumnName = "id_comp_componente")
    @ManyToOne
    private CompetenciaComponente idCompComponente;
    @JoinColumn(name = "id_enunciado", referencedColumnName = "id_enunciado")
    @ManyToOne
    private Enunciados idEnunciado;
    @JoinColumn(name = "id_tipo_pregunta", referencedColumnName = "id_tipo_pregunta")
    @ManyToOne
    private TipoPreguntas idTipoPregunta;
    @OneToMany(mappedBy = "idPregunta")
    private List<Respuesta> respuestaList;

    public Preguntas() {
    }

    public Preguntas(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public Preguntas(Integer idPregunta, String texto, Date createdAt, Date updatedAt) {
        this.idPregunta = idPregunta;
        this.texto = texto;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Short getNivel() {
        return nivel;
    }

    public void setNivel(Short nivel) {
        this.nivel = nivel;
    }

    public Short getProfudizacion() {
        return profudizacion;
    }

    public void setProfudizacion(Short profudizacion) {
        this.profudizacion = profudizacion;
    }

    public Short getPrivado() {
        return privado;
    }

    public void setPrivado(Short privado) {
        this.privado = privado;
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

    public List<PreguntaGrado> getPreguntaGradoList() {
        return preguntaGradoList;
    }

    public void setPreguntaGradoList(List<PreguntaGrado> preguntaGradoList) {
        this.preguntaGradoList = preguntaGradoList;
    }

    public CompetenciaComponente getIdCompComponente() {
        return idCompComponente;
    }

    public void setIdCompComponente(CompetenciaComponente idCompComponente) {
        this.idCompComponente = idCompComponente;
    }

    public Enunciados getIdEnunciado() {
        return idEnunciado;
    }

    public void setIdEnunciado(Enunciados idEnunciado) {
        this.idEnunciado = idEnunciado;
    }

    public TipoPreguntas getIdTipoPregunta() {
        return idTipoPregunta;
    }

    public void setIdTipoPregunta(TipoPreguntas idTipoPregunta) {
        this.idTipoPregunta = idTipoPregunta;
    }

    public List<Respuesta> getRespuestaList() {
        return respuestaList;
    }

    public void setRespuestaList(List<Respuesta> respuestaList) {
        this.respuestaList = respuestaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPregunta != null ? idPregunta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Preguntas)) {
            return false;
        }
        Preguntas other = (Preguntas) object;
        if ((this.idPregunta == null && other.idPregunta != null) || (this.idPregunta != null && !this.idPregunta.equals(other.idPregunta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Preguntas[ idPregunta=" + idPregunta + " ]";
    }
    
}
