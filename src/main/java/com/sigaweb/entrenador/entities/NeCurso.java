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
@Table(name = "ne_curso")
@NamedQueries({
    @NamedQuery(name = "NeCurso.findAll", query = "SELECT n FROM NeCurso n")})
public class NeCurso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_curso")
    private Integer idCurso;
    @Column(name = "codigo_dane_sede")
    private String codigoDaneSede;
    @Column(name = "codigo_dane")
    private String codigoDane;
    @Basic(optional = false)
    @Column(name = "vigencia")
    private short vigencia;
    @Basic(optional = false)
    @Column(name = "estadogrupo")
    private boolean estadogrupo;
    @Column(name = "nro_documentod")
    private String nroDocumentod;
    @Column(name = "iddemetodologia")
    private Integer iddemetodologia;
    @Column(name = "iddeespecialidad")
    private Integer iddeespecialidad;
    @Column(name = "id_grado")
    private Integer idGrado;
    @Column(name = "id_grupo")
    private String idGrupo;
    @Column(name = "id_jornada")
    private Integer idJornada;
    @Column(name = "id_metodologia")
    private Integer idMetodologia;
    @Column(name = "libro")
    private Short libro;
    @Column(name = "estado")
    private Boolean estado;
    @Column(name = "fecha_creado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreado;
    @Column(name = "fecha_modificado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificado;
    @OneToMany(mappedBy = "idCurso")
    private List<MeRenovacionmatricula> meRenovacionmatriculaList;

    public NeCurso() {
    }

    public NeCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public NeCurso(Integer idCurso, short vigencia, boolean estadogrupo) {
        this.idCurso = idCurso;
        this.vigencia = vigencia;
        this.estadogrupo = estadogrupo;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getCodigoDaneSede() {
        return codigoDaneSede;
    }

    public void setCodigoDaneSede(String codigoDaneSede) {
        this.codigoDaneSede = codigoDaneSede;
    }

    public String getCodigoDane() {
        return codigoDane;
    }

    public void setCodigoDane(String codigoDane) {
        this.codigoDane = codigoDane;
    }

    public short getVigencia() {
        return vigencia;
    }

    public void setVigencia(short vigencia) {
        this.vigencia = vigencia;
    }

    public boolean getEstadogrupo() {
        return estadogrupo;
    }

    public void setEstadogrupo(boolean estadogrupo) {
        this.estadogrupo = estadogrupo;
    }

    public String getNroDocumentod() {
        return nroDocumentod;
    }

    public void setNroDocumentod(String nroDocumentod) {
        this.nroDocumentod = nroDocumentod;
    }

    public Integer getIddemetodologia() {
        return iddemetodologia;
    }

    public void setIddemetodologia(Integer iddemetodologia) {
        this.iddemetodologia = iddemetodologia;
    }

    public Integer getIddeespecialidad() {
        return iddeespecialidad;
    }

    public void setIddeespecialidad(Integer iddeespecialidad) {
        this.iddeespecialidad = iddeespecialidad;
    }

    public Integer getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Integer idGrado) {
        this.idGrado = idGrado;
    }

    public String getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Integer idJornada) {
        this.idJornada = idJornada;
    }

    public Integer getIdMetodologia() {
        return idMetodologia;
    }

    public void setIdMetodologia(Integer idMetodologia) {
        this.idMetodologia = idMetodologia;
    }

    public Short getLibro() {
        return libro;
    }

    public void setLibro(Short libro) {
        this.libro = libro;
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

    public List<MeRenovacionmatricula> getMeRenovacionmatriculaList() {
        return meRenovacionmatriculaList;
    }

    public void setMeRenovacionmatriculaList(List<MeRenovacionmatricula> meRenovacionmatriculaList) {
        this.meRenovacionmatriculaList = meRenovacionmatriculaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCurso != null ? idCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NeCurso)) {
            return false;
        }
        NeCurso other = (NeCurso) object;
        if ((this.idCurso == null && other.idCurso != null) || (this.idCurso != null && !this.idCurso.equals(other.idCurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.NeCurso[ idCurso=" + idCurso + " ]";
    }
    
}
