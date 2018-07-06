package br.com.ottol.service.ms;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import br.com.ottol.entity.MS;
import br.com.ottol.entity.Megasenanumero;
import br.com.ottol.entity.Numero;

public class JGDerivadoValidacao {

	private Integer concurso;
	private Collection<Numero> numeros;

	public JGDerivadoValidacao(Integer concurso, Collection<Numero> numeros) {
		super();
		this.concurso = concurso;
		this.numeros = numeros;
	}

	public JGDerivadoValidacao(MS mS) {
		this.concurso = mS.getConcurso();
		this.numeros = mS.getMegasenanumeroCollection().stream().map(m -> m.getNumero()).collect(Collectors.toList());
	}

	public JGDerivadoValidacao() {
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

	public void setNumeros(List<Numero> numeros) {
		this.numeros = numeros;
	}

	public void setNumerosFromMs(Collection<Megasenanumero> megasenanumeros) {
		this.numeros = megasenanumeros.stream().map(m -> m.getNumero()).collect(Collectors.toList());
	}

	@Override
	public String toString() {
		String s = this.numeros != null ? Arrays.toString(this.numeros.toArray()) : "";
		return "Concurso : " + this.concurso + "  - Números : " + s;
	}

}
