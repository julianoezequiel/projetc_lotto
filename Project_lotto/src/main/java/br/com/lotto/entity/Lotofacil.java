/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lotto.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juliano
 */
@Entity
@Table(name = "lotofacil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lotofacil.findAll", query = "SELECT l FROM Lotofacil l")
    , @NamedQuery(name = "Lotofacil.findByIdlotofacil", query = "SELECT l FROM Lotofacil l WHERE l.idlotofacil = :idlotofacil")
    , @NamedQuery(name = "Lotofacil.findByConcurso", query = "SELECT l FROM Lotofacil l WHERE l.concurso = :concurso")
    , @NamedQuery(name = "Lotofacil.findByDatasorteio", query = "SELECT l FROM Lotofacil l WHERE l.datasorteio = :datasorteio")
    , @NamedQuery(name = "Lotofacil.findByArrecadacaototal", query = "SELECT l FROM Lotofacil l WHERE l.arrecadacaototal = :arrecadacaototal")
    , @NamedQuery(name = "Lotofacil.findByGanhadores15numeros", query = "SELECT l FROM Lotofacil l WHERE l.ganhadores15numeros = :ganhadores15numeros")
    , @NamedQuery(name = "Lotofacil.findByCidade", query = "SELECT l FROM Lotofacil l WHERE l.cidade = :cidade")
    , @NamedQuery(name = "Lotofacil.findByUf", query = "SELECT l FROM Lotofacil l WHERE l.uf = :uf")
    , @NamedQuery(name = "Lotofacil.findByGanhadores14numeros", query = "SELECT l FROM Lotofacil l WHERE l.ganhadores14numeros = :ganhadores14numeros")
    , @NamedQuery(name = "Lotofacil.findByGanhadores13numeros", query = "SELECT l FROM Lotofacil l WHERE l.ganhadores13numeros = :ganhadores13numeros")
    , @NamedQuery(name = "Lotofacil.findByGanhadores12numeros", query = "SELECT l FROM Lotofacil l WHERE l.ganhadores12numeros = :ganhadores12numeros")
    , @NamedQuery(name = "Lotofacil.findByGanhadores11numeros", query = "SELECT l FROM Lotofacil l WHERE l.ganhadores11numeros = :ganhadores11numeros")
    , @NamedQuery(name = "Lotofacil.findByValorrateio15numeros", query = "SELECT l FROM Lotofacil l WHERE l.valorrateio15numeros = :valorrateio15numeros")
    , @NamedQuery(name = "Lotofacil.findByValorrateio14numeros", query = "SELECT l FROM Lotofacil l WHERE l.valorrateio14numeros = :valorrateio14numeros")
    , @NamedQuery(name = "Lotofacil.findByValorrateio13numeros", query = "SELECT l FROM Lotofacil l WHERE l.valorrateio13numeros = :valorrateio13numeros")
    , @NamedQuery(name = "Lotofacil.findByValorrateio12numeros", query = "SELECT l FROM Lotofacil l WHERE l.valorrateio12numeros = :valorrateio12numeros")
    , @NamedQuery(name = "Lotofacil.findByValorrateio11numeros", query = "SELECT l FROM Lotofacil l WHERE l.valorrateio11numeros = :valorrateio11numeros")
    , @NamedQuery(name = "Lotofacil.findByAcumulado15numeros", query = "SELECT l FROM Lotofacil l WHERE l.acumulado15numeros = :acumulado15numeros")
    , @NamedQuery(name = "Lotofacil.findByEstimativapremio", query = "SELECT l FROM Lotofacil l WHERE l.estimativapremio = :estimativapremio")
    , @NamedQuery(name = "Lotofacil.findByValoracumuladoespecial", query = "SELECT l FROM Lotofacil l WHERE l.valoracumuladoespecial = :valoracumuladoespecial")})
public class Lotofacil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idlotofacil")
    private Integer idlotofacil;
    @Column(name = "concurso")
    private Integer concurso;
    @Column(name = "datasorteio")
    @Temporal(TemporalType.DATE)
    private Date datasorteio;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "arrecadacaototal")
    private BigDecimal arrecadacaototal;
    @Column(name = "ganhadores15numeros")
    private Integer ganhadores15numeros;
    @Size(max = 400)
    @Column(name = "cidade")
    private String cidade;
    @Size(max = 4)
    @Column(name = "uf")
    private String uf;
    @Column(name = "ganhadores14numeros")
    private Integer ganhadores14numeros;
    @Column(name = "ganhadores13numeros")
    private Integer ganhadores13numeros;
    @Column(name = "ganhadores12numeros")
    private Integer ganhadores12numeros;
    @Column(name = "ganhadores11numeros")
    private Integer ganhadores11numeros;
    @Column(name = "valorrateio15numeros")
    private BigDecimal valorrateio15numeros;
    @Column(name = "valorrateio14numeros")
    private BigDecimal valorrateio14numeros;
    @Column(name = "valorrateio13numeros")
    private BigDecimal valorrateio13numeros;
    @Column(name = "valorrateio12numeros")
    private BigDecimal valorrateio12numeros;
    @Column(name = "valorrateio11numeros")
    private BigDecimal valorrateio11numeros;
    @Column(name = "acumulado15numeros")
    private BigDecimal acumulado15numeros;
    @Column(name = "estimativapremio")
    private BigDecimal estimativapremio;
    @Column(name = "valoracumuladoespecial")
    private BigDecimal valoracumuladoespecial;
    @JoinTable(name = "lotofacilnumero", joinColumns = {
        @JoinColumn(name = "lotoFacillidlotofacil", referencedColumnName = "idlotofacil")}, inverseJoinColumns = {
        @JoinColumn(name = "numeroidnumero", referencedColumnName = "idnumero")})
    @ManyToMany
    private Collection<Numero> numeroCollection;
    @OneToMany(mappedBy = "lotolacilidconcursolotofacil")
    private Collection<Palpite> palpiteCollection;

    public Lotofacil() {
    }

    public Lotofacil(Integer idlotofacil) {
        this.idlotofacil = idlotofacil;
    }

    public Integer getIdlotofacil() {
        return idlotofacil;
    }

    public void setIdlotofacil(Integer idlotofacil) {
        this.idlotofacil = idlotofacil;
    }

    public Integer getConcurso() {
        return concurso;
    }

    public void setConcurso(Integer concurso) {
        this.concurso = concurso;
    }

    public Date getDatasorteio() {
        return datasorteio;
    }

    public void setDatasorteio(Date datasorteio) {
        this.datasorteio = datasorteio;
    }

    public BigDecimal getArrecadacaototal() {
        return arrecadacaototal;
    }

    public void setArrecadacaototal(BigDecimal arrecadacaototal) {
        this.arrecadacaototal = arrecadacaototal;
    }

    public Integer getGanhadores15numeros() {
        return ganhadores15numeros;
    }

    public void setGanhadores15numeros(Integer ganhadores15numeros) {
        this.ganhadores15numeros = ganhadores15numeros;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Integer getGanhadores14numeros() {
        return ganhadores14numeros;
    }

    public void setGanhadores14numeros(Integer ganhadores14numeros) {
        this.ganhadores14numeros = ganhadores14numeros;
    }

    public Integer getGanhadores13numeros() {
        return ganhadores13numeros;
    }

    public void setGanhadores13numeros(Integer ganhadores13numeros) {
        this.ganhadores13numeros = ganhadores13numeros;
    }

    public Integer getGanhadores12numeros() {
        return ganhadores12numeros;
    }

    public void setGanhadores12numeros(Integer ganhadores12numeros) {
        this.ganhadores12numeros = ganhadores12numeros;
    }

    public Integer getGanhadores11numeros() {
        return ganhadores11numeros;
    }

    public void setGanhadores11numeros(Integer ganhadores11numeros) {
        this.ganhadores11numeros = ganhadores11numeros;
    }

    public BigDecimal getValorrateio15numeros() {
        return valorrateio15numeros;
    }

    public void setValorrateio15numeros(BigDecimal valorrateio15numeros) {
        this.valorrateio15numeros = valorrateio15numeros;
    }

    public BigDecimal getValorrateio14numeros() {
        return valorrateio14numeros;
    }

    public void setValorrateio14numeros(BigDecimal valorrateio14numeros) {
        this.valorrateio14numeros = valorrateio14numeros;
    }

    public BigDecimal getValorrateio13numeros() {
        return valorrateio13numeros;
    }

    public void setValorrateio13numeros(BigDecimal valorrateio13numeros) {
        this.valorrateio13numeros = valorrateio13numeros;
    }

    public BigDecimal getValorrateio12numeros() {
        return valorrateio12numeros;
    }

    public void setValorrateio12numeros(BigDecimal valorrateio12numeros) {
        this.valorrateio12numeros = valorrateio12numeros;
    }

    public BigDecimal getValorrateio11numeros() {
        return valorrateio11numeros;
    }

    public void setValorrateio11numeros(BigDecimal valorrateio11numeros) {
        this.valorrateio11numeros = valorrateio11numeros;
    }

    public BigDecimal getAcumulado15numeros() {
        return acumulado15numeros;
    }

    public void setAcumulado15numeros(BigDecimal acumulado15numeros) {
        this.acumulado15numeros = acumulado15numeros;
    }

    public BigDecimal getEstimativapremio() {
        return estimativapremio;
    }

    public void setEstimativapremio(BigDecimal estimativapremio) {
        this.estimativapremio = estimativapremio;
    }

    public BigDecimal getValoracumuladoespecial() {
        return valoracumuladoespecial;
    }

    public void setValoracumuladoespecial(BigDecimal valoracumuladoespecial) {
        this.valoracumuladoespecial = valoracumuladoespecial;
    }

    @XmlTransient
    public Collection<Numero> getNumeroCollection() {
        return numeroCollection;
    }

    public void setNumeroCollection(Collection<Numero> numeroCollection) {
        this.numeroCollection = numeroCollection;
    }

    @XmlTransient
    public Collection<Palpite> getPalpiteCollection() {
        return palpiteCollection;
    }

    public void setPalpiteCollection(Collection<Palpite> palpiteCollection) {
        this.palpiteCollection = palpiteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlotofacil != null ? idlotofacil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lotofacil)) {
            return false;
        }
        Lotofacil other = (Lotofacil) object;
        if ((this.idlotofacil == null && other.idlotofacil != null) || (this.idlotofacil != null && !this.idlotofacil.equals(other.idlotofacil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.lotto.entity.Lotofacil[ idlotofacil=" + idlotofacil + " ]";
    }
    
}
