package br.com.lotto.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the concurso_loto_facil database table.
 * 
 */
@Entity
@Table(name="concurso_loto_facil")
@NamedQuery(name="ConcursoLotoFacil.findAll", query="SELECT c FROM ConcursoLotoFacil c")
public class ConcursoLotoFacil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CONCURSO_LOTO_FACIL_IDCONCURSO_LOTO_FACIL_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONCURSO_LOTO_FACIL_IDCONCURSO_LOTO_FACIL_GENERATOR")
	private int idConcurso_Loto_Facil;

	private BigDecimal acumulado_15_Numeros;

	private BigDecimal arrecadacao_Total;

	private String cidade;

	private int concurso;

	@Temporal(TemporalType.DATE)
	private Date data_Sorteio;

	private BigDecimal estimativa_Premio;

	private int ganhadores_11_Numeros;

	private int ganhadores_12_Numeros;

	private int ganhadores_13_Numeros;

	private int ganhadores_14_Numeros;

	private int ganhadores_15_Numeros;

	private String uf;

	private BigDecimal valor_Acumulado_Especial;

	private BigDecimal valor_Rateio_11_Numeros;

	private BigDecimal valor_Rateio_12_Numeros;

	private BigDecimal valor_Rateio_13_Numeros;

	private BigDecimal valor_Rateio_14_Numeros;

	private BigDecimal valor_Rateio_15_Numeros;

	public ConcursoLotoFacil() {
	}

	public int getIdConcurso_Loto_Facil() {
		return this.idConcurso_Loto_Facil;
	}

	public void setIdConcurso_Loto_Facil(int idConcurso_Loto_Facil) {
		this.idConcurso_Loto_Facil = idConcurso_Loto_Facil;
	}

	public BigDecimal getAcumulado_15_Numeros() {
		return this.acumulado_15_Numeros;
	}

	public void setAcumulado_15_Numeros(BigDecimal acumulado_15_Numeros) {
		this.acumulado_15_Numeros = acumulado_15_Numeros;
	}

	public BigDecimal getArrecadacao_Total() {
		return this.arrecadacao_Total;
	}

	public void setArrecadacao_Total(BigDecimal arrecadacao_Total) {
		this.arrecadacao_Total = arrecadacao_Total;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public int getConcurso() {
		return this.concurso;
	}

	public void setConcurso(int concurso) {
		this.concurso = concurso;
	}

	public Date getData_Sorteio() {
		return this.data_Sorteio;
	}

	public void setData_Sorteio(Date data_Sorteio) {
		this.data_Sorteio = data_Sorteio;
	}

	public BigDecimal getEstimativa_Premio() {
		return this.estimativa_Premio;
	}

	public void setEstimativa_Premio(BigDecimal estimativa_Premio) {
		this.estimativa_Premio = estimativa_Premio;
	}

	public int getGanhadores_11_Numeros() {
		return this.ganhadores_11_Numeros;
	}

	public void setGanhadores_11_Numeros(int ganhadores_11_Numeros) {
		this.ganhadores_11_Numeros = ganhadores_11_Numeros;
	}

	public int getGanhadores_12_Numeros() {
		return this.ganhadores_12_Numeros;
	}

	public void setGanhadores_12_Numeros(int ganhadores_12_Numeros) {
		this.ganhadores_12_Numeros = ganhadores_12_Numeros;
	}

	public int getGanhadores_13_Numeros() {
		return this.ganhadores_13_Numeros;
	}

	public void setGanhadores_13_Numeros(int ganhadores_13_Numeros) {
		this.ganhadores_13_Numeros = ganhadores_13_Numeros;
	}

	public int getGanhadores_14_Numeros() {
		return this.ganhadores_14_Numeros;
	}

	public void setGanhadores_14_Numeros(int ganhadores_14_Numeros) {
		this.ganhadores_14_Numeros = ganhadores_14_Numeros;
	}

	public int getGanhadores_15_Numeros() {
		return this.ganhadores_15_Numeros;
	}

	public void setGanhadores_15_Numeros(int ganhadores_15_Numeros) {
		this.ganhadores_15_Numeros = ganhadores_15_Numeros;
	}

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public BigDecimal getValor_Acumulado_Especial() {
		return this.valor_Acumulado_Especial;
	}

	public void setValor_Acumulado_Especial(BigDecimal valor_Acumulado_Especial) {
		this.valor_Acumulado_Especial = valor_Acumulado_Especial;
	}

	public BigDecimal getValor_Rateio_11_Numeros() {
		return this.valor_Rateio_11_Numeros;
	}

	public void setValor_Rateio_11_Numeros(BigDecimal valor_Rateio_11_Numeros) {
		this.valor_Rateio_11_Numeros = valor_Rateio_11_Numeros;
	}

	public BigDecimal getValor_Rateio_12_Numeros() {
		return this.valor_Rateio_12_Numeros;
	}

	public void setValor_Rateio_12_Numeros(BigDecimal valor_Rateio_12_Numeros) {
		this.valor_Rateio_12_Numeros = valor_Rateio_12_Numeros;
	}

	public BigDecimal getValor_Rateio_13_Numeros() {
		return this.valor_Rateio_13_Numeros;
	}

	public void setValor_Rateio_13_Numeros(BigDecimal valor_Rateio_13_Numeros) {
		this.valor_Rateio_13_Numeros = valor_Rateio_13_Numeros;
	}

	public BigDecimal getValor_Rateio_14_Numeros() {
		return this.valor_Rateio_14_Numeros;
	}

	public void setValor_Rateio_14_Numeros(BigDecimal valor_Rateio_14_Numeros) {
		this.valor_Rateio_14_Numeros = valor_Rateio_14_Numeros;
	}

	public BigDecimal getValor_Rateio_15_Numeros() {
		return this.valor_Rateio_15_Numeros;
	}

	public void setValor_Rateio_15_Numeros(BigDecimal valor_Rateio_15_Numeros) {
		this.valor_Rateio_15_Numeros = valor_Rateio_15_Numeros;
	}

}