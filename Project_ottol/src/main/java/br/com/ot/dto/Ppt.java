package br.com.ot.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ot.entity.Lotofacil;
import br.com.ot.entity.MS;

public class Ppt implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	private Integer idpalpite;

	private Integer limiteAcerto = 4;

	private Lotofacil lotolacilidconcursolotofacil;
	private ConfiguracoesDTO configuracoesDTO = new ConfiguracoesDTO();

	private MS megasenaidconcurso;
	private List<Integer> s = null;
	private Integer c = null;
	private Integer init = 0;
	// MA+,MA-,MF+,MF-,LMA(x,y),LMB(x,y),LMC(x,y),LMD(x,y),LME(x,y)
	private List<Integer> conf = null;

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

	public Integer getC() {
		return c;
	}

	public void setC(Integer c) {
		this.c = c;
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

	public List<Integer> getS() {
		return s;
	}

	public void setS(List<Integer> s) {
		this.s = s;
	}

	public List<Integer> getConf() {
		return conf;
	}

	public void setConf(List<Integer> conf) {
		this.conf = conf;
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

	@Override
	public Ppt clone() {
		try {
			return (Ppt) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}
