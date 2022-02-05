package br.com.ot.dto;

import java.io.Serializable;
import java.util.List;

public class ResultadoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Ppt ppt;
	private List<RespostaValidacao> validar;
	private Integer T = 0;
	private Integer A = 0;
	private Integer V = 0;

	public ResultadoDTO() {
	}

	public ResultadoDTO(Ppt ppt, List<RespostaValidacao> validar2, Integer t, Integer a, Integer v) {
		this.ppt = ppt;
		this.validar = validar2;
		this.T = t;
		this.A = a;
		this.V = v;
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

	public Ppt getPpt() {
		return ppt;
	}

	public void setPpt(Ppt ppt) {
		this.ppt = ppt;
	}

	public Integer getT() {
		return T;
	}

	public void setT(Integer t) {
		T = t;
	}

	public Integer getA() {
		return A;
	}

	public void setA(Integer a) {
		A = a;
	}

	public Integer getV() {
		return V;
	}

	public void setV(Integer v) {
		V = v;
	}

}
