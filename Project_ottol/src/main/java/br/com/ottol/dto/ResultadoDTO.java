package br.com.ottol.dto;

import java.util.List;

public class ResultadoDTO {

	private PalpiteDTO palpiteDTO;
	private List<RespostaValidacao> validar;

	public ResultadoDTO() {
	}

	public ResultadoDTO(PalpiteDTO ppt, List<RespostaValidacao> validar2) {
		this.palpiteDTO = ppt;
		this.validar = validar2;
	}

	public PalpiteDTO getPalpiteDTO() {
		return palpiteDTO;
	}

	public void setPalpiteDTO(PalpiteDTO palpiteDTO) {
		this.palpiteDTO = palpiteDTO;
	}

	public List<RespostaValidacao> getValidar() {
		return validar;
	}

	public void setValidar(List<RespostaValidacao> validar) {
		this.validar = validar;
	}

}
