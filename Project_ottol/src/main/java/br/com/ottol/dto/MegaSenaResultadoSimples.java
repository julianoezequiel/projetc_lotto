package br.com.ottol.dto;

public class MegaSenaResultadoSimples {

	private Integer concurso;
	private String numerosSorteados;

	public MegaSenaResultadoSimples(Integer concurso, String numerosSorteados) {
		super();
		this.concurso = concurso;
		this.numerosSorteados = numerosSorteados;
	}

	public Integer getConcurso() {
		return concurso;
	}

	public void setConcurso(Integer concurso) {
		this.concurso = concurso;
	}

	public String getNumerosSorteados() {
		return numerosSorteados;
	}

	public void setNumerosSorteados(String numerosSorteados) {
		this.numerosSorteados = numerosSorteados;
	}

}
