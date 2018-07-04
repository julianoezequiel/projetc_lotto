/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lotto.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idpalpite;
    
    @JoinColumn(name = "lotolacilidconcursolotofacil", referencedColumnName = "idlotofacil")
    @ManyToOne
    private Lotofacil lotolacilidconcursolotofacil;
    
    @JoinColumn(name = "megasenaidconcurso", referencedColumnName = "idconcurso")
    @ManyToOne
    private MS megasenaidconcurso;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "palpite")
    private Collection<Palpitenumero> palpitenumeroCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpalpite")
    private Collection<Configuracoes> configuracoesCollection;

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

    public MS getMegasenaidconcurso() {
        return megasenaidconcurso;
    }

    public void setMegasenaidconcurso(MS megasenaidconcurso) {
        this.megasenaidconcurso = megasenaidconcurso;
    }

    @XmlTransient
    public Collection<Palpitenumero> getPalpitenumeroCollection() {
        return palpitenumeroCollection;
    }

    public void setPalpitenumeroCollection(Collection<Palpitenumero> palpitenumeroCollection) {
        this.palpitenumeroCollection = palpitenumeroCollection;
    }

    @XmlTransient
    public Collection<Configuracoes> getConfiguracoesCollection() {
        return configuracoesCollection;
    }

    public void setConfiguracoesCollection(Collection<Configuracoes> configuracoesCollection) {
        this.configuracoesCollection = configuracoesCollection;
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
