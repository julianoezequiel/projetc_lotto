package br.com.ottol.utils;

public enum DEZ {

	DEFAULT(0), A(1), B(2), C(3), D(4), E(5), F(6), G(7), H(8), I(9), J(10);

	private Integer valor;

	private DEZ(Integer dez) {
		this.valor = dez;
	}

	public Integer getValor() {
		return valor;
	}

	public static DEZ fromInt(Integer valor) {
		for (DEZ dez : values()) {
			if (dez.valor == valor) {
				return dez;
			}
		}
		return DEZ.DEFAULT;
	}

}
