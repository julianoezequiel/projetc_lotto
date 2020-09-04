package br.com.ottol.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ottol.service.ms.comb.validacao.ListaA;

public class ConfiguracoesDTO implements Cloneable {

	private Integer maisAtrazado = 0;
	private Integer maisFrequente = 0;
	private Integer menosAtrazado = 0;
	private Integer menosFrequente = 0;

	private Limite limiteA = new Limite(0, 0);
	private Limite limiteB = new Limite(0, 0);
	private Limite limiteC = new Limite(0, 1);
	private Limite limiteD = new Limite(1, 4);
	private Limite limiteE = new Limite(2, 23);

	public Integer getMaisAtrazado() {
		return maisAtrazado;
	}

	public void setMaisAtrazado(Integer maisAtrazado) {
		this.maisAtrazado = maisAtrazado;
	}

	public Integer getMaisFrequente() {
		return maisFrequente;
	}

	public void setMaisFrequente(Integer maisFrequente) {
		this.maisFrequente = maisFrequente;
	}

	public Integer getMenosAtrazado() {
		return menosAtrazado;
	}

	public void setMenosAtrazado(Integer menosAtrazado) {
		this.menosAtrazado = menosAtrazado;
	}

	public Integer getMenosFrequente() {
		return menosFrequente;
	}

	public void setMenosFrequente(Integer menosFrequente) {
		this.menosFrequente = menosFrequente;
	}

	public Limite getLimiteA() {
		return limiteA;
	}

	public void setLimiteA(Limite limiteA) {
		this.limiteA = limiteA;
	}

	public Limite getLimiteB() {
		return limiteB;
	}

	public void setLimiteB(Limite limiteB) {
		this.limiteB = limiteB;
	}

	public Limite getLimiteC() {
		return limiteC;
	}

	public void setLimiteC(Limite limiteC) {
		this.limiteC = limiteC;
	}

	public Limite getLimiteD() {
		return limiteD;
	}

	public void setLimiteD(Limite limiteD) {
		this.limiteD = limiteD;
	}

	public Limite getLimiteE() {
		return limiteE;
	}

	public void setLimiteE(Limite limiteE) {
		this.limiteE = limiteE;
	}

	@Override
	public String toString() {

		return "maisAtrazado :" + maisAtrazado + " maisFrequente :" + maisFrequente + " menosAtrazado : "
				+ menosAtrazado + " menosFrequente:" + menosFrequente;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	public ConfiguracoesDTO newIntance() {
		try {
			return (ConfiguracoesDTO) this.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

}
