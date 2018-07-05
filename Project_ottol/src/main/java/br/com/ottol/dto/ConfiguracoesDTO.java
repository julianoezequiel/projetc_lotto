package br.com.ottol.dto;

public class ConfiguracoesDTO implements Cloneable{

	private Integer maisAtrazado = 0;
	private Integer maisFrequente = 0;
	private Integer menosAtrazado = 0;
	private Integer menosFrequente = 0;

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

	@Override
	public String toString() {
		
		return "maisAtrazado :" + maisAtrazado 
				+ " maisFrequente :" + maisFrequente
				+ " menosAtrazado : " + menosAtrazado
				+ " menosFrequente:" + menosFrequente;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
	public ConfiguracoesDTO newIntance(){
		try {
			return (ConfiguracoesDTO) this.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}
