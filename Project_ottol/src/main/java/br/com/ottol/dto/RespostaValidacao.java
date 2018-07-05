package br.com.ottol.dto;

import br.com.ottol.service.Validacao;

public class RespostaValidacao {

	private Boolean aprovado;
	private String validacao;
	
	public RespostaValidacao(Boolean aprovado) {
		super();
		this.aprovado = aprovado;
	}
	public RespostaValidacao(String validacao, Boolean aprovado) {
		super();
		this.validacao = validacao;
		this.aprovado = aprovado;
	}
	public String getValidacao() {
		return validacao;
	}
	public void setValidacao(String validacao) {
		this.validacao = validacao;
	}
	public Boolean getAprovado() {
		return aprovado;
	}
	public void setAprovado(Boolean aprovado) {
		this.aprovado = aprovado;
	}
	
	
}
