package br.com.ottol.dto;

import java.util.Collection;

import br.com.ottol.entity.Lotofacil;
import br.com.ottol.entity.MS;

public class PalpiteDTO {

	private Integer idpalpite;

	private Lotofacil lotolacilidconcursolotofacil;

	private MS megasenaidconcurso;

	private Collection<NumeroDTO> numeroCollection;

	private Collection<ConfiguracoesDTO> configuracoesCollection;

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

}
