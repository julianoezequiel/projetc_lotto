package br.com.ottol.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Limite {

	private Integer min;
	private Integer max;

	public Limite() {
	}

	public Limite(Integer min, Integer max) {
		super();
		this.max = max;
		this.min = min;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	@JsonIgnore
	public Boolean isValido(Integer valor) {
		return valor <= this.max && valor >= this.min;
	}

}
