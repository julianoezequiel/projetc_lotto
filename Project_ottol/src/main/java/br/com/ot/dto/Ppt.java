package br.com.ot.dto;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ot.entity.Lotofacil;
import br.com.ot.entity.MS;

public class Ppt {

	private Integer idpalpite;

	private Integer limiteAcerto = 4;

	private Lotofacil lotolacilidconcursolotofacil;
	private ConfiguracoesDTO configuracoesDTO = new ConfiguracoesDTO();

	private MS megasenaidconcurso;
	private Integer[] s = null;
	private Integer c = null;
	private Integer init = 0;
	// MA+,MA-,MF+,MF-,LMA(x,y),LMB(x,y),LMC(x,y),LMD(x,y),LME(x,y)
	private Integer[] conf = null;

	private Collection<NumeroDTO> numeroCollection = new ArrayList<>();

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

	public Integer[] getConf() {
		return conf;
	}

	public void setConf(Integer[] conf) {
		this.conf = conf;
	}

	public ConfiguracoesDTO getConfiguracoesDTO() {
		return configuracoesDTO;
	}

	public void setConfiguracoesDTO(ConfiguracoesDTO configuracoesDTO) {
		this.configuracoesDTO = configuracoesDTO;
	}

	public Integer getLimiteAcerto() {
		return limiteAcerto;
	}

	public void setLimiteAcerto(Integer tipoAcerto) {
		this.limiteAcerto = tipoAcerto;
	}

	public Integer getInit() {
		return init;
	}

	public void setInit(Integer init) {
		this.init = init;
	}

	@JsonIgnore
	public void corrigirPpt() {
		if (this.conf != null) {
			this.configuracoesDTO = new ConfiguracoesDTO(this.conf);
		}

		if (this.c != null) {
			this.megasenaidconcurso = new MS(this.c);
		}

		if (this.s != null) {
			for (Integer i : this.s) {
				this.getNumeroCollection().add(new NumeroDTO(i));
			}
		}
	}

}
