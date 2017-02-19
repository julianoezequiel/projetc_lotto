package br.com.lotto.dto;

import java.util.ArrayList;
import java.util.Collection;

import br.com.lotto.entity.Numero;

public class Jogos {

	private Integer concurso;
	private Collection<Numero> numeros = new ArrayList<>();

	
	public Jogos(Integer concurso, Collection<Numero> numeros) {
		super();
		this.concurso = concurso;
		this.numeros = numeros;
	}
	
	public Jogos(Collection<Numero> numeros) {
		super();
		this.numeros = numeros;
	}

	public Jogos() {
		// TODO Auto-generated constructor stub
	}

	public Integer getConcurso() {
		return concurso;
	}

	public void setConcurso(Integer concurso) {
		this.concurso = concurso;
	}

	public Collection<Numero> getNumeros() {
		return numeros;
	}

	public void setNumeros(Collection<Numero> numeros) {
		this.numeros = numeros;
	}

}
