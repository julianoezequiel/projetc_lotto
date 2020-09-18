package br.com.ot.dto;

public class MSResultadoSimples {

	private Integer concurso;
	private String numerosSorteados;

	public MSResultadoSimples(Integer concurso, String numerosSorteados) {
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
