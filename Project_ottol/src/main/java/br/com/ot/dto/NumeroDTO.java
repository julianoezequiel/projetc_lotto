package br.com.ot.dto;

public class NumeroDTO {

	public NumeroDTO() {
	}

	private Integer idNumero;

	public NumeroDTO(Integer idNumero) {
		super();
		this.idNumero = idNumero;
	}

	public Integer getIdNumero() {
		return idNumero;
	}

	public void setIdNumero(Integer idNumero) {
		this.idNumero = idNumero;
	}

	@Override
	public String toString() {
		return  idNumero.toString() ;
	}

}
