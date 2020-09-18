package br.com.ot.dto;

import java.util.ArrayList;
import java.util.LinkedList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AtrasoDTO {

	private Integer conc;
	private Integer numero;
	private Integer ultimoAtraso;
	private Integer maiorJaRegistrado;
	private Integer atual;
	@JsonIgnore
	private LinkedList<Integer> ciclo = new LinkedList<>();
	private Double mediaCiclo = 0d;

	public AtrasoDTO(Integer numero, Integer atraso) {
		this.numero = numero;
		this.atual = atraso;
	}

	public Integer getUltimoAtraso() {
		return ultimoAtraso;
	}

	public Integer getNumero() {
		return numero;
	}

	public Integer getMaiorJaRegistrado() {
		return maiorJaRegistrado;
	}

	public void setMaiorJaRegistrado(Integer maiorJaRegistrado) {
		this.maiorJaRegistrado = maiorJaRegistrado;
	}

	public Integer getAtual() {
		return atual;
	}

	public void setAtual(Integer atual) {
		this.atual = atual;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public void setUltimoAtraso(Integer ultimoAtraso) {
		this.ultimoAtraso = ultimoAtraso;
	}

	public Integer getConc() {
		return conc;
	}

	public void setConc(Integer conc) {
		this.conc = conc;
	}

	public LinkedList<Integer> getCiclo() {
		return ciclo;
	}

	public void setCiclo(LinkedList<Integer> ciclo) {
		this.ciclo = ciclo;
	}

	public Double getMediaCiclo() {
		return mediaCiclo;
	}

	public void setMediaCiclo(Double mediaCiclo) {
		this.mediaCiclo = mediaCiclo;
	}
}
