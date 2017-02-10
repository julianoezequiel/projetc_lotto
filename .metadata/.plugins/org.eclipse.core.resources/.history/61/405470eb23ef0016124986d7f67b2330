package model;

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

	@EmbeddedId
	private ConcursoLotoFacilPK id;

	private BigDecimal acumulado_15_Números;

	private BigDecimal arrecadacao_Total;

	private String cidade;

	private int concurso;

	@Temporal(TemporalType.DATE)
	private Date data_Sorteio;

	private BigDecimal estimativa_Premio;

	private int ganhadores_11_Números;

	private int ganhadores_12_Números;

	private int ganhadores_13_Números;

	private int ganhadores_14_Números;

	private int ganhadores_15_Números;

	private String uf;

	private BigDecimal valor_Acumulado_Especial;

	private BigDecimal valor_Rateio_11_Números;

	private BigDecimal valor_Rateio_12_Números;

	private BigDecimal valor_Rateio_13_Números;

	private BigDecimal valor_Rateio_14_Números;

	private BigDecimal valor_Rateio_15_Números;

	//bi-directional many-to-one association to Numero
	@ManyToOne
	private Numero numero;

	public ConcursoLotoFacil() {
	}

	public ConcursoLotoFacilPK getId() {
		return this.id;
	}

	public void setId(ConcursoLotoFacilPK id) {
		this.id = id;
	}

	public BigDecimal getAcumulado_15_Números() {
		return this.acumulado_15_Números;
	}

	public void setAcumulado_15_Números(BigDecimal acumulado_15_Números) {
		this.acumulado_15_Números = acumulado_15_Números;
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

	public int getGanhadores_11_Números() {
		return this.ganhadores_11_Números;
	}

	public void setGanhadores_11_Números(int ganhadores_11_Números) {
		this.ganhadores_11_Números = ganhadores_11_Números;
	}

	public int getGanhadores_12_Números() {
		return this.ganhadores_12_Números;
	}

	public void setGanhadores_12_Números(int ganhadores_12_Números) {
		this.ganhadores_12_Números = ganhadores_12_Números;
	}

	public int getGanhadores_13_Números() {
		return this.ganhadores_13_Números;
	}

	public void setGanhadores_13_Números(int ganhadores_13_Números) {
		this.ganhadores_13_Números = ganhadores_13_Números;
	}

	public int getGanhadores_14_Números() {
		return this.ganhadores_14_Números;
	}

	public void setGanhadores_14_Números(int ganhadores_14_Números) {
		this.ganhadores_14_Números = ganhadores_14_Números;
	}

	public int getGanhadores_15_Números() {
		return this.ganhadores_15_Números;
	}

	public void setGanhadores_15_Números(int ganhadores_15_Números) {
		this.ganhadores_15_Números = ganhadores_15_Números;
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

	public BigDecimal getValor_Rateio_11_Números() {
		return this.valor_Rateio_11_Números;
	}

	public void setValor_Rateio_11_Números(BigDecimal valor_Rateio_11_Números) {
		this.valor_Rateio_11_Números = valor_Rateio_11_Números;
	}

	public BigDecimal getValor_Rateio_12_Números() {
		return this.valor_Rateio_12_Números;
	}

	public void setValor_Rateio_12_Números(BigDecimal valor_Rateio_12_Números) {
		this.valor_Rateio_12_Números = valor_Rateio_12_Números;
	}

	public BigDecimal getValor_Rateio_13_Números() {
		return this.valor_Rateio_13_Números;
	}

	public void setValor_Rateio_13_Números(BigDecimal valor_Rateio_13_Números) {
		this.valor_Rateio_13_Números = valor_Rateio_13_Números;
	}

	public BigDecimal getValor_Rateio_14_Números() {
		return this.valor_Rateio_14_Números;
	}

	public void setValor_Rateio_14_Números(BigDecimal valor_Rateio_14_Números) {
		this.valor_Rateio_14_Números = valor_Rateio_14_Números;
	}

	public BigDecimal getValor_Rateio_15_Números() {
		return this.valor_Rateio_15_Números;
	}

	public void setValor_Rateio_15_Números(BigDecimal valor_Rateio_15_Números) {
		this.valor_Rateio_15_Números = valor_Rateio_15_Números;
	}

	public Numero getNumero() {
		return this.numero;
	}

	public void setNumero(Numero numero) {
		this.numero = numero;
	}

}