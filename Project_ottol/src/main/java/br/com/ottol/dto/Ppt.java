package br.com.ottol.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.glassfish.jersey.internal.guava.Sets;

import br.com.ottol.entity.Lotofacil;
import br.com.ottol.entity.MS;

public class Ppt {

	private Integer idpalpite;

	private Lotofacil lotolacilidconcursolotofacil;

	private MS megasenaidconcurso;
	private Integer[] s = null;
	private Integer c = null;

	private Collection<NumeroDTO> numeroCollection = new ArrayList<>();

	private Collection<ConfiguracoesDTO> configuracoesCollection = new ArrayList<>();

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

	public Collection<NumeroDTO> getNumeroCollection() {
		return numeroCollection;
	}

	public void setNumeroCollection(Collection<NumeroDTO> numeroCollection) {
		this.numeroCollection = numeroCollection;
	}

	public Collection<ConfiguracoesDTO> getConfiguracoesCollection() {
		return configuracoesCollection;
	}

	public void setConfiguracoesCollection(Collection<ConfiguracoesDTO> configuracoesCollection) {
		this.configuracoesCollection = configuracoesCollection;
	}

	public Integer[] getS() {
		return s;
	}

	public void setS(Integer[] s) {
		this.s = s;
	}

	public Integer getC() {
		return c;
	}

	public void setC(Integer c) {
		this.c = c;
	}

}
