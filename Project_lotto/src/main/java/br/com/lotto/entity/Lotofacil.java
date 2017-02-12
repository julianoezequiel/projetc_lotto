package br.com.lotto.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the lotofacil database table.
 * 
 */
@Entity
@NamedQuery(name="Lotofacil.findAll", query="SELECT l FROM Lotofacil l")
public class Lotofacil implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id_LotoFacil;
	private BigDecimal acumulado_15_Numeros;
	private BigDecimal arrecadacao_Total;
	private String cidade;
	private int concurso;
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
	private Numero numero1;
	private Numero numero2;
	private Numero numero3;
	private Numero numero4;
	private Numero numero5;
	private Numero numero6;
	private Numero numero7;
	private Numero numero8;
	private Numero numero9;
	private Numero numero10;
	private Numero numero11;
	private Numero numero12;
	private Numero numero13;
	private Numero numero14;
	private Numero numero15;
	private List<Palpite> palpites;

	public Lotofacil() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId_LotoFacil() {
		return this.id_LotoFacil;
	}

	public void setId_LotoFacil(int id_LotoFacil) {
		this.id_LotoFacil = id_LotoFacil;
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


	//bi-directional many-to-one association to Numero
	@ManyToOne
	@JoinColumn(name="7_Dezena")
	public Numero getNumero7() {
		return this.numero7;
	}

	public void setNumero7(Numero numero7) {
		this.numero7 = numero7;
	}


	//bi-directional many-to-one association to Numero
	@ManyToOne
	@JoinColumn(name="8_Dezena")
	public Numero getNumero8() {
		return this.numero8;
	}

	public void setNumero8(Numero numero8) {
		this.numero8 = numero8;
	}


	//bi-directional many-to-one association to Numero
	@ManyToOne
	@JoinColumn(name="9_Dezena")
	public Numero getNumero9() {
		return this.numero9;
	}

	public void setNumero9(Numero numero9) {
		this.numero9 = numero9;
	}


	//bi-directional many-to-one association to Numero
	@ManyToOne
	@JoinColumn(name="10_Dezena")
	public Numero getNumero10() {
		return this.numero10;
	}

	public void setNumero10(Numero numero10) {
		this.numero10 = numero10;
	}


	//bi-directional many-to-one association to Numero
	@ManyToOne
	@JoinColumn(name="11_Dezena")
	public Numero getNumero11() {
		return this.numero11;
	}

	public void setNumero11(Numero numero11) {
		this.numero11 = numero11;
	}


	//bi-directional many-to-one association to Numero
	@ManyToOne
	@JoinColumn(name="12_Dezena")
	public Numero getNumero12() {
		return this.numero12;
	}

	public void setNumero12(Numero numero12) {
		this.numero12 = numero12;
	}


	//bi-directional many-to-one association to Numero
	@ManyToOne
	@JoinColumn(name="13_Dezena")
	public Numero getNumero13() {
		return this.numero13;
	}

	public void setNumero13(Numero numero13) {
		this.numero13 = numero13;
	}


	//bi-directional many-to-one association to Numero
	@ManyToOne
	@JoinColumn(name="14_Dezena")
	public Numero getNumero14() {
		return this.numero14;
	}

	public void setNumero14(Numero numero14) {
		this.numero14 = numero14;
	}


	//bi-directional many-to-one association to Numero
	@ManyToOne
	@JoinColumn(name="15_Dezena")
	public Numero getNumero15() {
		return this.numero15;
	}

	public void setNumero15(Numero numero15) {
		this.numero15 = numero15;
	}


	//bi-directional many-to-one association to Palpite
	@OneToMany(mappedBy="lotofacil")
	@JsonIgnore
	public List<Palpite> getPalpites() {
		return this.palpites;
	}

	public void setPalpites(List<Palpite> palpites) {
		this.palpites = palpites;
	}

	public Palpite addPalpite(Palpite palpite) {
		getPalpites().add(palpite);
		palpite.setLotofacil(this);

		return palpite;
	}

	public Palpite removePalpite(Palpite palpite) {
		getPalpites().remove(palpite);
		palpite.setLotofacil(null);

		return palpite;
	}

}