package br.com.ot.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.ot.entity.Megasenanumero;
import br.com.ot.entity.Numero;

public class Jgs {

	private Integer concurso;
	private Collection<Megasenanumero> numeros = new ArrayList<>();

	public Jgs(Integer concurso, Collection<Megasenanumero> numeros) {
		super();
		this.concurso = concurso;
		this.numeros = numeros;
	}

	public Jgs(Collection<Megasenanumero> numeros) {
		super();
		this.numeros = numeros;
	}

	public Jgs() {
		// TODO Auto-generated constructor stub
	}

	public Integer getConcurso() {
		return concurso;
	}

	public void setConcurso(Integer concurso) {
		this.concurso = concurso;
	}

	public Collection<Megasenanumero> getNumeros() {
		return numeros;
	}

	public void setNumeros(Collection<Megasenanumero> numeros) {
		this.numeros = numeros;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<>();
		if (!this.numeros.isEmpty()) {
			this.numeros.stream().forEach(n -> {
				list.add(n.toString());
			});
		}
		return "Concurso : " + this.getConcurso() + " - " + list.toString();
	}

}
