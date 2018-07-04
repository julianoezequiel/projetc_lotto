package br.com.lotto.service.ms;

import java.util.Collection;
import java.util.stream.Collectors;

import br.com.lotto.entity.MS;
import br.com.lotto.entity.Megasenanumero;
import br.com.lotto.entity.Numero;

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

	public void setNumeros(Collection<Numero> numeros) {
		this.numeros = numeros;
	}

	public void setNumerosFromMs(Collection<Megasenanumero> megasenanumeros) {
		this.numeros = megasenanumeros.stream().map(m -> m.getNumero()).collect(Collectors.toList());
	}

}
