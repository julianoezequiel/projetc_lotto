package br.com.ot.dto;

public class AnaliseFreqResult {

	private final Integer sorteio;
	private final Integer numero;
	private final Integer frequencia;
	private final Double media;

	public Double getMedia() {
		return media;
	}

	public AnaliseFreqResult(Integer sorteio, Integer numero, Integer frequencia, Double media) {
		super();
		this.sorteio = sorteio;
		this.numero = numero;
		this.frequencia = frequencia;
		this.media = media;
	}

	public Integer getSorteio() {
		return sorteio;
	}

	public Integer getNumero() {
		return numero;
	}

	public Integer getFrequencia() {
		return frequencia;
	}
	
	
}
