/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lotto.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juliano
 */
@Entity
@Table(name = "palpite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Palpite.findAll", query = "SELECT p FROM Palpite p")
    , @NamedQuery(name = "Palpite.findByIdpalpite", query = "SELECT p FROM Palpite p WHERE p.idpalpite = :idpalpite")})
public class Palpite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idpalpite")
    private Integer idpalpite;
    @JoinColumn(name = "lotolacilidconcursolotofacil", referencedColumnName = "idlotofacil")
    @ManyToOne
    private Lotofacil lotolacilidconcursolotofacil;
    @JoinColumn(name = "megasenaidconcurso", referencedColumnName = "idconcurso")
    @ManyToOne
    private Megasena megasenaidconcurso;
    @JoinColumn(name = "numeroidnumero", referencedColumnName = "idnumero")
    @ManyToOne
    private Numero numeroidnumero;

    public Palpite() {
    }

    public Palpite(Integer idpalpite) {
        this.idpalpite = idpalpite;
    }

    public Integer getIdpalpite() {
        return idpalpite;
    }

    public void setIdpalpite(Integer idpalpite) {
        this.idpalpite = idpalpite;
    }

    public Lotofacil getLotolacilidconcursolotofacil() {
        return lotolacilidconcursolotofacil;
    }

    public void setLotolacilidconcursolotofacil(Lotofacil lotolacilidconcursolotofacil) {
        this.lotolacilidconcursolotofacil = lotolacilidconcursolotofacil;
    }

    public Megasena getMegasenaidconcurso() {
        return megasenaidconcurso;
    }

    public void setMegasenaidconcurso(Megasena megasenaidconcurso) {
        this.megasenaidconcurso = megasenaidconcurso;
    }

    public Numero getNumeroidnumero() {
        return numeroidnumero;
    }

    public void setNumeroidnumero(Numero numeroidnumero) {
        this.numeroidnumero = numeroidnumero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpalpite != null ? idpalpite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Palpite)) {
            return false;
        }
        Palpite other = (Palpite) object;
        if ((this.idpalpite == null && other.idpalpite != null) || (this.idpalpite != null && !this.idpalpite.equals(other.idpalpite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.lotto.entity.Palpite[ idpalpite=" + idpalpite + " ]";
    }
    
}
