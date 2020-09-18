package br.com.ot.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class FrequenciaDTO {

	private Integer numero;
	private BigDecimal frequencia;
	private Double media;

	public FrequenciaDTO(Integer numero, BigDecimal frequencia) {
		this.numero = numero;
		this.frequencia = frequencia;
	}
	
	

	public FrequenciaDTO(Integer numero, BigDecimal frequencia, Double media) {
		super();
		this.numero = numero;
		this.frequencia = frequencia;
		this.media = media;
	}



	public Integer getNumero() {
		return numero;
	}

	public BigDecimal getFrequencia() {
		return frequencia;
	}

	public Double getMedia() {
		return media;
	}
}
