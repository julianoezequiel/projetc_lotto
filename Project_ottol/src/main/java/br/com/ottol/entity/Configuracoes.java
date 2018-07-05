/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package br.com.ottol.entity;

import java.io.Serializable;

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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juliano
 */
@Entity
@Table(name = "configuracoes")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Configuracoes.findAll", query = "SELECT c FROM Configuracoes c"),
		@NamedQuery(name = "Configuracoes.findByIdconfiguracoes", query = "SELECT c FROM Configuracoes c WHERE c.idconfiguracoes = :idconfiguracoes") })
public class Configuracoes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "idconfiguracoes")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idconfiguracoes;

	@Column(name = "maisfrequente")
	private Integer maisfrequente;

	@Column(name = "menosfrequente")
	private Integer menosfrequente;

	@Column(name = "atraso")
	private Integer atraso;

	@JoinColumn(name = "idpalpite", referencedColumnName = "idpalpite")
	@ManyToOne(optional = false)
	private Palpite idpalpite;

	public Configuracoes() {
	}

	public Configuracoes(Integer idconfiguracoes) {
		this.idconfiguracoes = idconfiguracoes;
	}

	public Integer getIdconfiguracoes() {
		return idconfiguracoes;
	}

	public void setIdconfiguracoes(Integer idconfiguracoes) {
		this.idconfiguracoes = idconfiguracoes;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idconfiguracoes != null ? idconfiguracoes.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Configuracoes)) {
			return false;
		}
		Configuracoes other = (Configuracoes) object;
		if ((this.idconfiguracoes == null && other.idconfiguracoes != null)
				|| (this.idconfiguracoes != null && !this.idconfiguracoes.equals(other.idconfiguracoes))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.lotto.entity.Configuracoes[ idconfiguracoes=" + idconfiguracoes + " ]";
	}

	public Integer getMaisfrequente() {
		return maisfrequente;
	}

	public void setMaisfrequente(Integer maisfrequente) {
		this.maisfrequente = maisfrequente;
	}

	public Integer getMenosfrequente() {
		return menosfrequente;
	}

	public void setMenosfrequente(Integer menosfrequente) {
		this.menosfrequente = menosfrequente;
	}

	public Integer getAtraso() {
		return atraso;
	}

	public void setAtrassssso(Integer atraso) {
		this.atraso = atraso;
	}

	public Palpite getIdpalpite() {
		return idpalpite;
	}

	public void setIdpalpite(Palpite idpalpite) {
		this.idpalpite = idpalpite;
	}

}
