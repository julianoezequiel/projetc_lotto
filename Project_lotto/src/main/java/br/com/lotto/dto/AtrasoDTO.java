package br.com.lotto.dto;

public class AtrasoDTO {

	private Integer numero;
	private Integer atraso;

	public AtrasoDTO(Integer numero, Integer atraso) {
		this.numero = numero;
		this.atraso = atraso;
	}

	public Integer getAtraso() {
		return atraso;
	}

	public Integer getNumero() {
		return numero;
	}

}
