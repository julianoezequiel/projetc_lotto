package br.com.lotto.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the concurso_mega_sena database table.
 * 
 */
@Entity
@Table(name="concurso_mega_sena")
@NamedQuery(name="ConcursoMegaSena.findAll", query="SELECT c FROM ConcursoMegaSena c")
public class ConcursoMegaSena implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idConcurso;
	private byte acumulado;
	private BigDecimal acumulado_Mega_da_Virada;
	private String arrecadacao_Total;
	private String cidade;
	private int concurso;
	private Date data_Sorteio;
	private BigDecimal estimativa_Premio;
	private int ganhadores_Quadra;
	private int ganhadores_Quina;
	private int ganhadores_Sena;
	private BigDecimal rateio_Quadra;
	private BigDecimal rateio_Quina;
	private BigDecimal rateio_Sena;
	private String uf;
	private BigDecimal valor_Acumulado;
	private Numero numero1;
	private Numero numero2;
	private Numero numero3;
	private Numero numero4;
	private Numero numero5;
	private Numero numero6;
	private List<Tipo> tipos;

	public ConcursoMegaSena() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIdConcurso() {
		return this.idConcurso;
	}

	public void setIdConcurso(int idConcurso) {
		this.idConcurso = idConcurso;
	}


	public byte getAcumulado() {
		return this.acumulado;
	}

	public void setAcumulado(byte acumulado) {
		this.acumulado = acumulado;
	}


	public BigDecimal getAcumulado_Mega_da_Virada() {
		return this.acumulado_Mega_da_Virada;
	}

	public void setAcumulado_Mega_da_Virada(BigDecimal acumulado_Mega_da_Virada) {
		this.acumulado_Mega_da_Virada = acumulado_Mega_da_Virada;
	}


	public String getArrecadacao_Total() {
		return this.arrecadacao_Total;
	}

	public void setArrecadacao_Total(String arrecadacao_Total) {
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


	@Temporal(TemporalType.DATE)
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


	public int getGanhadores_Quadra() {
		return this.ganhadores_Quadra;
	}

	public void setGanhadores_Quadra(int ganhadores_Quadra) {
		this.ganhadores_Quadra = ganhadores_Quadra;
	}


	public int getGanhadores_Quina() {
		return this.ganhadores_Quina;
	}

	public void setGanhadores_Quina(int ganhadores_Quina) {
		this.ganhadores_Quina = ganhadores_Quina;
	}


	public int getGanhadores_Sena() {
		return this.ganhadores_Sena;
	}

	public void setGanhadores_Sena(int ganhadores_Sena) {
		this.ganhadores_Sena = ganhadores_Sena;
	}


	public BigDecimal getRateio_Quadra() {
		return this.rateio_Quadra;
	}

	public void setRateio_Quadra(BigDecimal rateio_Quadra) {
		this.rateio_Quadra = rateio_Quadra;
	}


	public BigDecimal getRateio_Quina() {
		return this.rateio_Quina;
	}

	public void setRateio_Quina(BigDecimal rateio_Quina) {
		this.rateio_Quina = rateio_Quina;
	}


	public BigDecimal getRateio_Sena() {
		return this.rateio_Sena;
	}

	public void setRateio_Sena(BigDecimal rateio_Sena) {
		this.rateio_Sena = rateio_Sena;
	}


	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}


	public BigDecimal getValor_Acumulado() {
		return this.valor_Acumulado;
	}

	public void setValor_Acumulado(BigDecimal valor_Acumulado) {
		this.valor_Acumulado = valor_Acumulado;
	}


	//bi-directional many-to-one association to Numero
	@ManyToOne
	@JoinColumn(name="1_Dezena")
	public Numero getNumero1() {
		return this.numero1;
	}

	public void setNumero1(Numero numero1) {
		this.numero1 = numero1;
	}


	//bi-directional many-to-one association to Numero
	@ManyToOne
	@JoinColumn(name="2_Dezena")
	public Numero getNumero2() {
		return this.numero2;
	}

	public void setNumero2(Numero numero2) {
		this.numero2 = numero2;
	}


	//bi-directional many-to-one association to Numero
	@ManyToOne
	@JoinColumn(name="3_Dezena")
	public Numero getNumero3() {
		return this.numero3;
	}

	public void setNumero3(Numero numero3) {
		this.numero3 = numero3;
	}


	//bi-directional many-to-one association to Numero
	@ManyToOne
	@JoinColumn(name="4_Dezena")
	public Numero getNumero4() {
		return this.numero4;
	}

	public void setNumero4(Numero numero4) {
		this.numero4 = numero4;
	}


	//bi-directional many-to-one association to Numero
	@ManyToOne
	@JoinColumn(name="5_Dezena")
	public Numero getNumero5() {
		return this.numero5;
	}

	public void setNumero5(Numero numero5) {
		this.numero5 = numero5;
	}


	//bi-directional many-to-one association to Numero
	@ManyToOne
	@JoinColumn(name="6_Dezena")
	public Numero getNumero6() {
		return this.numero6;
	}

	public void setNumero6(Numero numero6) {
		this.numero6 = numero6;
	}


	//bi-directional many-to-one association to Tipo
	@OneToMany(mappedBy="concursoMegaSena")
	public List<Tipo> getTipos() {
		return this.tipos;
	}

	public void setTipos(List<Tipo> tipos) {
		this.tipos = tipos;
	}

	public Tipo addTipo(Tipo tipo) {
		getTipos().add(tipo);
		tipo.setConcursoMegaSena(this);

		return tipo;
	}

	public Tipo removeTipo(Tipo tipo) {
		getTipos().remove(tipo);
		tipo.setConcursoMegaSena(null);

		return tipo;
	}

}