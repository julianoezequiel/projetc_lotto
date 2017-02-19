package br.com.lotto.dto;

import java.math.BigInteger;

public class FrequenciaDTO {

	private Integer numero;
	private BigInteger frequencia;

	public FrequenciaDTO(Integer numero, BigInteger frequencia) {
		this.numero = numero;
		this.frequencia = frequencia;
	}

	public Integer getNumero() {
		return numero;
	}

	public BigInteger getFrequencia() {
		return frequencia;
	}
}
