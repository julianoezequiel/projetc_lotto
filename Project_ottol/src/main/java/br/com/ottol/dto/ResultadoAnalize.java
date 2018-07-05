package br.com.ottol.dto;

import br.com.ottol.entity.Configuracoes;
import br.com.ottol.service.Validacao;

public class ResultadoAnalize {

	private Validacao validacao;

	private Configuracoes configuracoes;

	private Float acerto;

	public Validacao getValidacao() {
		return validacao;
	}

	public void setValidacao(Validacao validacao) {
		this.validacao = validacao;
	}

	public Configuracoes getConfiguracoes() {
		return configuracoes;
	}

	public void setConfiguracoes(Configuracoes configuracoes) {
		this.configuracoes = configuracoes;
	}

	public Float getAcerto() {
		return acerto;
	}

	public void setAcerto(Float acerto) {
		this.acerto = acerto;
	}

}
