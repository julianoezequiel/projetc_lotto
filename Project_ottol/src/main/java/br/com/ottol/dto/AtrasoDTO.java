package br.com.ottol.dto;

public class AtrasoDTO {

	private Integer numero;
	private Integer ultimoAtraso;
	private Integer maiorJaRegistrado;
	private Integer atual;

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
	
	

}
