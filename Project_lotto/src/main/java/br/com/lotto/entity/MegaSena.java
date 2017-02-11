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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Juliano
 */
@Entity
@Table(name = "megasena")
@XmlRootElement
public class MegaSena implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idConcurso")
    private Integer idConcurso;
    @Column(name = "Concurso")
    private Integer concurso;
    @Column(name = "Data_Sorteio")
    @Temporal(TemporalType.DATE)
    private Date dataSorteio;
    @Size(max = 45)
    @Column(name = "Arrecadacao_Total")
    private String arrecadacaoTotal;
    @Column(name = "Ganhadores_Sena")
    private Integer ganhadoresSena;
    @Size(max = 400)
    @Column(name = "Cidade")
    private String cidade;
    @Size(max = 200)
    @Column(name = "UF")
    private String uf;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Rateio_Sena")
    private BigDecimal rateioSena;
    @Column(name = "Ganhadores_Quina")
    private Integer ganhadoresQuina;
    @Column(name = "Rateio_Quina")
    private BigDecimal rateioQuina;
    @Column(name = "Ganhadores_Quadra")
    private Integer ganhadoresQuadra;
    @Column(name = "Rateio_Quadra")
    private BigDecimal rateioQuadra;
    @Column(name = "Acumulado")
    private Boolean acumulado;
    @Column(name = "Valor_Acumulado")
    private BigDecimal valorAcumulado;
    @Column(name = "Estimativa_Premio")
    private BigDecimal estimativaPremio;
    @Column(name = "Acumulado_Mega_da_Virada")
    private BigDecimal acumuladoMegadaVirada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "concursoidConcurso")
    private Collection<Tipo> tipoCollection;
    @JoinColumn(name = "1_Dezena", referencedColumnName = "idNumero")
    @ManyToOne
    private Numero dezena;
    @JoinColumn(name = "2_Dezena", referencedColumnName = "idNumero")
    @ManyToOne
    private Numero dezena1;
    @JoinColumn(name = "3_Dezena", referencedColumnName = "idNumero")
    @ManyToOne
    private Numero dezena2;
    @JoinColumn(name = "4_Dezena", referencedColumnName = "idNumero")
    @ManyToOne
    private Numero dezena3;
    @JoinColumn(name = "5_Dezena", referencedColumnName = "idNumero")
    @ManyToOne
    private Numero dezena4;
    @JoinColumn(name = "6_Dezena", referencedColumnName = "idNumero")
    @ManyToOne
    private Numero dezena5;

    public MegaSena() {
    }

    public MegaSena(Integer idConcurso) {
        this.idConcurso = idConcurso;
    }

    public Integer getIdConcurso() {
        return idConcurso;
    }

    public void setIdConcurso(Integer idConcurso) {
        this.idConcurso = idConcurso;
    }

    public Integer getConcurso() {
        return concurso;
    }

    public void setConcurso(Integer concurso) {
        this.concurso = concurso;
    }

    public Date getDataSorteio() {
        return dataSorteio;
    }

    public void setDataSorteio(Date dataSorteio) {
        this.dataSorteio = dataSorteio;
    }

    public String getArrecadacaoTotal() {
        return arrecadacaoTotal;
    }

    public void setArrecadacaoTotal(String arrecadacaoTotal) {
        this.arrecadacaoTotal = arrecadacaoTotal;
    }

    public Integer getGanhadoresSena() {
        return ganhadoresSena;
    }

    public void setGanhadoresSena(Integer ganhadoresSena) {
        this.ganhadoresSena = ganhadoresSena;
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

    public BigDecimal getRateioSena() {
        return rateioSena;
    }

    public void setRateioSena(BigDecimal rateioSena) {
        this.rateioSena = rateioSena;
    }

    public Integer getGanhadoresQuina() {
        return ganhadoresQuina;
    }

    public void setGanhadoresQuina(Integer ganhadoresQuina) {
        this.ganhadoresQuina = ganhadoresQuina;
    }

    public BigDecimal getRateioQuina() {
        return rateioQuina;
    }

    public void setRateioQuina(BigDecimal rateioQuina) {
        this.rateioQuina = rateioQuina;
    }

    public Integer getGanhadoresQuadra() {
        return ganhadoresQuadra;
    }

    public void setGanhadoresQuadra(Integer ganhadoresQuadra) {
        this.ganhadoresQuadra = ganhadoresQuadra;
    }

    public BigDecimal getRateioQuadra() {
        return rateioQuadra;
    }

    public void setRateioQuadra(BigDecimal rateioQuadra) {
        this.rateioQuadra = rateioQuadra;
    }

    public Boolean getAcumulado() {
        return acumulado;
    }

    public void setAcumulado(Boolean acumulado) {
        this.acumulado = acumulado;
    }

    public BigDecimal getValorAcumulado() {
        return valorAcumulado;
    }

    public void setValorAcumulado(BigDecimal valorAcumulado) {
        this.valorAcumulado = valorAcumulado;
    }

    public BigDecimal getEstimativaPremio() {
        return estimativaPremio;
    }

    public void setEstimativaPremio(BigDecimal estimativaPremio) {
        this.estimativaPremio = estimativaPremio;
    }

    public BigDecimal getAcumuladoMegadaVirada() {
        return acumuladoMegadaVirada;
    }

    public void setAcumuladoMegadaVirada(BigDecimal acumuladoMegadaVirada) {
        this.acumuladoMegadaVirada = acumuladoMegadaVirada;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Tipo> getTipoCollection() {
        return tipoCollection;
    }

    public void setTipoCollection(Collection<Tipo> tipoCollection) {
        this.tipoCollection = tipoCollection;
    }

    public Numero getDezena() {
        return dezena;
    }

    public void setDezena(Numero dezena) {
        this.dezena = dezena;
    }

    public Numero getDezena1() {
        return dezena1;
    }

    public void setDezena1(Numero dezena1) {
        this.dezena1 = dezena1;
    }

    public Numero getDezena2() {
        return dezena2;
    }

    public void setDezena2(Numero dezena2) {
        this.dezena2 = dezena2;
    }

    public Numero getDezena3() {
        return dezena3;
    }

    public void setDezena3(Numero dezena3) {
        this.dezena3 = dezena3;
    }

    public Numero getDezena4() {
        return dezena4;
    }

    public void setDezena4(Numero dezena4) {
        this.dezena4 = dezena4;
    }

    public Numero getDezena5() {
        return dezena5;
    }

    public void setDezena5(Numero dezena5) {
        this.dezena5 = dezena5;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConcurso != null ? idConcurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MegaSena)) {
            return false;
        }
        MegaSena other = (MegaSena) object;
        if ((this.idConcurso == null && other.idConcurso != null) || (this.idConcurso != null && !this.idConcurso.equals(other.idConcurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.lotto.entity.ConcursoMegaSena[ idConcurso=" + idConcurso + " ]";
    }
    
}
