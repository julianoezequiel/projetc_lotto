package br.com.ot.service.ms;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import br.com.ot.entity.MS;
import br.com.ot.entity.Megasenanumero;
import br.com.ot.entity.Numero;

public class JGDerivadoValidacao {

	private Integer concurso;
	private Collection<Numero> nums;
	private String num;

	public JGDerivadoValidacao(Integer concurso, Collection<Numero> nums) {
		super();
		this.concurso = concurso;
		this.nums = nums;
		this.num = Arrays.toString(nums.toArray());
	}

	public JGDerivadoValidacao(MS mS) {
		this.concurso = mS.getConcurso();
		this.nums = mS.getMegasenanumeroCollection().stream().map(m -> m.getNumero()).collect(Collectors.toList());
		this.num = Arrays.toString(this.nums.toArray());
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
		return nums;
	}

	public void setNumeros(List<Numero> nums) {
		this.nums = nums;
		this.num = Arrays.toString(this.nums.toArray());
	}

	public void setNumerosFromMs(Collection<Megasenanumero> mgsnums) {
		this.nums = mgsnums.stream().map(m -> m.getNumero()).collect(Collectors.toList());
		this.num = Arrays.toString(this.nums.toArray());
	}

	public String getNum() {
		return this.num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	@Override
	public String toString() {
		String s = this.nums != null ? Arrays.toString(this.nums.toArray()) : "";
		return "Concurso : " + this.concurso + "  - Números : " + s;
	}

}
