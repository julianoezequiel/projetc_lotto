/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ottol.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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

import br.com.ottol.dto.MSDTO;
import br.com.ottol.dto.NumeroDTO;

/**
 *
 * @author Juliano
 */
@Entity
@Table(name = "megasena")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "MS.findAll", query = "SELECT m FROM MS m"),
		@NamedQuery(name = "MS.findByIdconcurso", query = "SELECT m FROM MS m WHERE m.idconcurso = :idconcurso"),
		@NamedQuery(name = "MS.findByConcurso", query = "SELECT m FROM MS m WHERE m.concurso = :concurso"),
		@NamedQuery(name = "MS.findByDatasorteio", query = "SELECT m FROM MS m WHERE m.datasorteio = :datasorteio"),
		@NamedQuery(name = "MS.findByArrecadacaototal", query = "SELECT m FROM MS m WHERE m.arrecadacaototal = :arrecadacaototal"),
		@NamedQuery(name = "MS.findByGanhadoressena", query = "SELECT m FROM MS m WHERE m.ganhadoressena = :ganhadoressena"),
		@NamedQuery(name = "MS.findByCidade", query = "SELECT m FROM MS m WHERE m.cidade = :cidade"),
		@NamedQuery(name = "MS.findByUf", query = "SELECT m FROM MS m WHERE m.uf = :uf"),
		@NamedQuery(name = "MS.findByRateiosena", query = "SELECT m FROM MS m WHERE m.rateiosena = :rateiosena"),
		@NamedQuery(name = "MS.findByGanhadoresQuina", query = "SELECT m FROM MS m WHERE m.ganhadoresquina = :ganhadoresquina"),
		@NamedQuery(name = "MS.findByRateioquina", query = "SELECT m FROM MS m WHERE m.rateioquina = :rateioquina"),
		@NamedQuery(name = "MS.findByGanhadoresquadra", query = "SELECT m FROM MS m WHERE m.ganhadoresquadra = :ganhadoresquadra"),
		@NamedQuery(name = "MS.findByRateioquadra", query = "SELECT m FROM MS m WHERE m.rateioquadra = :rateioquadra"),
		@NamedQuery(name = "MS.findByAcumulado", query = "SELECT m FROM MS m WHERE m.acumulado = :acumulado"),
		@NamedQuery(name = "MS.findByValoracumulado", query = "SELECT m FROM MS m WHERE m.valoracumulado = :valoracumulado"),
		@NamedQuery(name = "MS.findByEstimativapremio", query = "SELECT m FROM MS m WHERE m.estimativapremio = :estimativapremio"),
		@NamedQuery(name = "MS.findByAcumuladomegadavirada", query = "SELECT m FROM MS m WHERE m.acumuladomegadavirada = :acumuladomegadavirada") })
public class MS implements Serializable {

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mS", fetch = FetchType.EAGER)
	private Collection<Megasenanumero> megasenanumeroCollection;

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "idconcurso")
	private Integer idconcurso;

	@Column(name = "concurso")
	private Integer concurso;

	@Column(name = "datasorteio")
	@Temporal(TemporalType.DATE)
	private Date datasorteio;

	@Size(max = 45)
	@Column(name = "arrecadacaototal")
	private String arrecadacaototal;

	@Column(name = "ganhadoressena")
	private Integer ganhadoressena;

	@Size(max = 400)
	@Column(name = "cidade")
	private String cidade;

	@Size(max = 200)
	@Column(name = "uf")
	private String uf;

	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "rateiosena")
	private BigDecimal rateiosena;

	@Column(name = "ganhadoresquina")
	private Integer ganhadoresquina;

	@Column(name = "rateioquina")
	private BigDecimal rateioquina;

	@Column(name = "ganhadoresquadra")
	private Integer ganhadoresquadra;

	@Column(name = "rateioquadra")
	private BigDecimal rateioquadra;

	@Column(name = "acumulado",columnDefinition = "TINYINT", length = 1)
	private Boolean acumulado;

	@Column(name = "valoracumulado")
	private BigDecimal valoracumulado;

	@Column(name = "estimativapremio")
	private BigDecimal estimativapremio;

	@Column(name = "acumuladomegadavirada")
	private BigDecimal acumuladomegadavirada;

	@OneToMany(mappedBy = "megasenaidconcurso")
	@JsonIgnore
	private Collection<Palpite> palpiteCollection;

	public MS() {
	}

	public MS(Integer idconcurso) {
		this.idconcurso = idconcurso;
	}

	public Integer getIdconcurso() {
		return idconcurso;
	}

	public void setIdconcurso(Integer idconcurso) {
		this.idconcurso = idconcurso;
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

	public String getArrecadacaototal() {
		return arrecadacaototal;
	}

	public void setArrecadacaototal(String arrecadacaototal) {
		this.arrecadacaototal = arrecadacaototal;
	}

	public Integer getGanhadoressena() {
		return ganhadoressena;
	}

	public void setGanhadoressena(Integer ganhadoressena) {
		this.ganhadoressena = ganhadoressena;
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

	public BigDecimal getRateiosena() {
		return rateiosena;
	}

	public void setRateiosena(BigDecimal rateiosena) {
		this.rateiosena = rateiosena;
	}

	public Integer getGanhadoresquina() {
		return ganhadoresquina;
	}

	public void setGanhadoresQuina(Integer ganhadoresquina) {
		this.ganhadoresquina = ganhadoresquina;
	}

	public BigDecimal getRateioquina() {
		return rateioquina;
	}

	public void setRateioquina(BigDecimal rateioquina) {
		this.rateioquina = rateioquina;
	}

	public Integer getGanhadoresquadra() {
		return ganhadoresquadra;
	}

	public void setGanhadoresquadra(Integer ganhadoresquadra) {
		this.ganhadoresquadra = ganhadoresquadra;
	}

	public BigDecimal getRateioquadra() {
		return rateioquadra;
	}

	public void setRateioquadra(BigDecimal rateioquadra) {
		this.rateioquadra = rateioquadra;
	}

	public Boolean getAcumulado() {
		return acumulado;
	}

	public void setAcumulado(Boolean acumulado) {
		this.acumulado = acumulado;
	}

	public BigDecimal getValoracumulado() {
		return valoracumulado;
	}

	public void setValoracumulado(BigDecimal valoracumulado) {
		this.valoracumulado = valoracumulado;
	}

	public BigDecimal getEstimativapremio() {
		return estimativapremio;
	}

	public void setEstimativapremio(BigDecimal estimativapremio) {
		this.estimativapremio = estimativapremio;
	}

	public BigDecimal getAcumuladomegadavirada() {
		return acumuladomegadavirada;
	}

	public void setAcumuladomegadavirada(BigDecimal acumuladomegadavirada) {
		this.acumuladomegadavirada = acumuladomegadavirada;
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
		hash += (idconcurso != null ? idconcurso.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof MS)) {
			return false;
		}
		MS other = (MS) object;
		if ((this.idconcurso == null && other.idconcurso != null)
				|| (this.idconcurso != null && !this.idconcurso.equals(other.idconcurso))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.lotto.entity.Megasena[ idconcurso=" + idconcurso + " ]";
	}

	public MSDTO toMegaSenaDTO() {
		MSDTO dto = new MSDTO();
		dto.setAcumulado(acumulado);
		dto.setAcumuladoMegadaVirada(acumuladomegadavirada);
		dto.setArrecadacaoTotal(arrecadacaototal);
		dto.setCidade(cidade);
		dto.setConcurso(concurso);
		dto.setDataSorteio(datasorteio);
		dto.setEstimativaPremio(estimativapremio);
		dto.setGanhadoresQuadra(ganhadoresquadra);
		dto.setGanhadoresQuina(ganhadoresquina);
		dto.setGanhadoresSena(ganhadoressena);
		Collection<NumeroDTO> numeroDTOList = new ArrayList<>();
		megasenanumeroCollection.stream().forEach(n -> {
			numeroDTOList.add(n.getNumero().toNumeroDTO());
		});
		dto.setNumerosSorteados(numeroDTOList);
		dto.setRateioQuadra(rateioquadra);
		dto.setRateioQuina(rateioquina);
		dto.setRateioSena(rateiosena);
		dto.setUf(uf);
		dto.setValorAcumulado(valoracumulado);
		return dto;

	}

	@XmlTransient
	public Collection<Megasenanumero> getMegasenanumeroCollection() {
		return megasenanumeroCollection;
	}

	public void setMegasenanumeroCollection(Collection<Megasenanumero> megasenanumeroCollection) {
		this.megasenanumeroCollection = megasenanumeroCollection;
	}
}
