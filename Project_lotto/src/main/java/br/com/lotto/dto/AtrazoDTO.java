package br.com.lotto.dto;

public class AtrazoDTO {

	private Integer numero;
	private Integer atrazo;

	public AtrazoDTO(Integer numero, Integer atrazo) {
		this.numero = numero;
		this.atrazo = atrazo;
	}

	public Integer getAtrazo() {
		return atrazo;
	}

	public Integer getNumero() {
		return numero;
	}

}
