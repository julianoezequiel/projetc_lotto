package br.com.ottol.dto;

import java.util.List;

public class ResultadoDTO {

	private Ppt ppt;
	private List<RespostaValidacao> validar;

	public ResultadoDTO() {
	}

	public ResultadoDTO(Ppt ppt, List<RespostaValidacao> validar2) {
		this.ppt = ppt;
		this.validar = validar2;
	}

	public Ppt getPalpiteDTO() {
		return ppt;
	}

	public void setPalpiteDTO(Ppt ppt) {
		this.ppt = ppt;
	}

	public List<RespostaValidacao> getValidar() {
		return validar;
	}

	public void setValidar(List<RespostaValidacao> validar) {
		this.validar = validar;
	}

}
