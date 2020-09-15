package br.com.ottol.dto;

public class RespostaValidacao {

	private Boolean aprovado;
	private String validacao;
	private Integer frequencia = 0;
	private Boolean desabilitado = false;

	public RespostaValidacao(Boolean aprovado) {
		super();
		this.aprovado = aprovado;
	}

	public RespostaValidacao(String validacao, Boolean aprovado) {
		super();
		this.validacao = validacao;
		this.aprovado = aprovado;
	}

	public RespostaValidacao(String validacao, Boolean aprovado, Integer f) {
		super();
		this.validacao = validacao;
		this.aprovado = aprovado;
		this.frequencia = f;
	}

	public RespostaValidacao(String validacao, boolean aprovado, boolean desabilitado) {
		this.validacao = validacao;
		this.aprovado = aprovado;
		this.desabilitado = desabilitado;
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

	public Integer getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(Integer frequencia) {
		this.frequencia = frequencia;
	}

	public Boolean getDesabilitado() {
		return desabilitado;
	}

	public void setDesabilitado(Boolean desabilitado) {
		this.desabilitado = desabilitado;
	}

}
