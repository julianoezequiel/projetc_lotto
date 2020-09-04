package br.com.ottol.dto;

import java.util.List;

import br.com.ottol.service.ms.comb.validacao.ListaA;
import br.com.ottol.service.ms.comb.validacao.ListaB;
import br.com.ottol.service.ms.comb.validacao.ListaC;
import br.com.ottol.service.ms.comb.validacao.ListaD;
import br.com.ottol.service.ms.comb.validacao.ListaE;

public class ResultResumido {

	private Integer idConc;
	private Integer freqlistaA;
	private Integer freqlistaB;
	private Integer freqlistaC;
	private Integer freqlistaD;
	private Integer freqlistaE;
	private Boolean aprovado;

	public ResultResumido() {
	}

	public ResultResumido(Integer idConc, Integer freqlistaA, Integer freqlistaB, Integer freqlistaC,
			Integer freqlistaD, Integer freqlistaE, Integer freqlistaF) {
		super();
		this.idConc = idConc;
		this.freqlistaA = freqlistaA;
		this.freqlistaB = freqlistaB;
		this.freqlistaC = freqlistaC;
		this.freqlistaD = freqlistaD;
		this.freqlistaE = freqlistaE;
	}

	public ResultResumido(Integer i, List<RespostaValidacao> validar) {
		this.idConc = i;
		this.freqlistaA = validar.stream().filter(f -> f.getValidacao().endsWith(ListaA.class.getSimpleName()))
				.findFirst().orElse(new RespostaValidacao(false)).getFrequencia();
		this.freqlistaB = validar.stream().filter(f -> f.getValidacao().endsWith(ListaB.class.getSimpleName()))
				.findFirst().orElse(new RespostaValidacao(false)).getFrequencia();
		this.freqlistaC = validar.stream().filter(f -> f.getValidacao().endsWith(ListaC.class.getSimpleName()))
				.findFirst().orElse(new RespostaValidacao(false)).getFrequencia();
		this.freqlistaD = validar.stream().filter(f -> f.getValidacao().endsWith(ListaD.class.getSimpleName()))
				.findFirst().orElse(new RespostaValidacao(false)).getFrequencia();
		this.freqlistaE = validar.stream().filter(f -> f.getValidacao().endsWith(ListaE.class.getSimpleName()))
				.findFirst().orElse(new RespostaValidacao(false)).getFrequencia();
		this.aprovado = validar.stream().allMatch(p -> p.getAprovado());
	}

	public Integer getIdConc() {
		return idConc;
	}

	public void setIdConc(Integer idConc) {
		this.idConc = idConc;
	}

	public Integer getFreqlistaA() {
		return freqlistaA;
	}

	public void setFreqlistaA(Integer freqlistaA) {
		this.freqlistaA = freqlistaA;
	}

	public Integer getFreqlistaB() {
		return freqlistaB;
	}

	public void setFreqlistaB(Integer freqlistaB) {
		this.freqlistaB = freqlistaB;
	}

	public Integer getFreqlistaC() {
		return freqlistaC;
	}

	public void setFreqlistaC(Integer freqlistaC) {
		this.freqlistaC = freqlistaC;
	}

	public Integer getFreqlistaD() {
		return freqlistaD;
	}

	public void setFreqlistaD(Integer freqlistaD) {
		this.freqlistaD = freqlistaD;
	}

	public Integer getFreqlistaE() {
		return freqlistaE;
	}

	public void setFreqlistaE(Integer freqlistaE) {
		this.freqlistaE = freqlistaE;
	}

	public Boolean getAprovado() {
		return aprovado;
	}

	public void setAprovado(Boolean aprovado) {
		this.aprovado = aprovado;
	}

	@Override
	public String toString() {
		return "ResultResumido [idConc=" + idConc + ", freqlistaA=" + freqlistaA + ", freqlistaB=" + freqlistaB
				+ ", freqlistaC=" + freqlistaC + ", freqlistaD=" + freqlistaD + ", freqlistaE=" + freqlistaE
				+ ", aprovado=" + aprovado + "]";
	}

}
