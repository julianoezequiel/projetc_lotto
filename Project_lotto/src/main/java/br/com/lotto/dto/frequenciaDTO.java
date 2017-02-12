package br.com.lotto.dto;

public class frequenciaDTO {

	private Integer numero;
	private Integer frequencia;

	public frequenciaDTO(Integer numero, Integer frequencia) {
		this.numero = numero;
		this.frequencia = frequencia;
	}

	public Integer getNumero() {
		return numero;
	}

	public Integer getFrequencia() {
		return frequencia;
	}
}
