package br.com.lotto.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the numero database table.
 * 
 */
@Entity
@NamedQuery(name="Numero.findAll", query="SELECT n FROM Numero n")
public class Numero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idNumero;

	private String descricao;

	private String extenso;

	private int num;

	//bi-directional many-to-one association to ConcursoMegaSena
	@OneToMany(mappedBy="numero1")
	private List<ConcursoMegaSena> concursoMegaSenas1;

	//bi-directional many-to-one association to ConcursoMegaSena
	@OneToMany(mappedBy="numero2")
	private List<ConcursoMegaSena> concursoMegaSenas2;

	//bi-directional many-to-one association to ConcursoMegaSena
	@OneToMany(mappedBy="numero3")
	private List<ConcursoMegaSena> concursoMegaSenas3;

	//bi-directional many-to-one association to ConcursoMegaSena
	@OneToMany(mappedBy="numero4")
	private List<ConcursoMegaSena> concursoMegaSenas4;

	//bi-directional many-to-one association to ConcursoMegaSena
	@OneToMany(mappedBy="numero5")
	private List<ConcursoMegaSena> concursoMegaSenas5;

	//bi-directional many-to-one association to ConcursoMegaSena
	@OneToMany(mappedBy="numero6")
	private List<ConcursoMegaSena> concursoMegaSenas6;

	//bi-directional many-to-one association to Palpite
	@OneToMany(mappedBy="numero")
	private List<Palpite> palpites;

	public Numero() {
	}

	public int getIdNumero() {
		return this.idNumero;
	}

	public void setIdNumero(int idNumero) {
		this.idNumero = idNumero;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getExtenso() {
		return this.extenso;
	}

	public void setExtenso(String extenso) {
		this.extenso = extenso;
	}

	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public List<ConcursoMegaSena> getConcursoMegaSenas1() {
		return this.concursoMegaSenas1;
	}

	public void setConcursoMegaSenas1(List<ConcursoMegaSena> concursoMegaSenas1) {
		this.concursoMegaSenas1 = concursoMegaSenas1;
	}

	public ConcursoMegaSena addConcursoMegaSenas1(ConcursoMegaSena concursoMegaSenas1) {
		getConcursoMegaSenas1().add(concursoMegaSenas1);
		concursoMegaSenas1.setNumero1(this);

		return concursoMegaSenas1;
	}

	public ConcursoMegaSena removeConcursoMegaSenas1(ConcursoMegaSena concursoMegaSenas1) {
		getConcursoMegaSenas1().remove(concursoMegaSenas1);
		concursoMegaSenas1.setNumero1(null);

		return concursoMegaSenas1;
	}

	public List<ConcursoMegaSena> getConcursoMegaSenas2() {
		return this.concursoMegaSenas2;
	}

	public void setConcursoMegaSenas2(List<ConcursoMegaSena> concursoMegaSenas2) {
		this.concursoMegaSenas2 = concursoMegaSenas2;
	}

	public ConcursoMegaSena addConcursoMegaSenas2(ConcursoMegaSena concursoMegaSenas2) {
		getConcursoMegaSenas2().add(concursoMegaSenas2);
		concursoMegaSenas2.setNumero2(this);

		return concursoMegaSenas2;
	}

	public ConcursoMegaSena removeConcursoMegaSenas2(ConcursoMegaSena concursoMegaSenas2) {
		getConcursoMegaSenas2().remove(concursoMegaSenas2);
		concursoMegaSenas2.setNumero2(null);

		return concursoMegaSenas2;
	}

	public List<ConcursoMegaSena> getConcursoMegaSenas3() {
		return this.concursoMegaSenas3;
	}

	public void setConcursoMegaSenas3(List<ConcursoMegaSena> concursoMegaSenas3) {
		this.concursoMegaSenas3 = concursoMegaSenas3;
	}

	public ConcursoMegaSena addConcursoMegaSenas3(ConcursoMegaSena concursoMegaSenas3) {
		getConcursoMegaSenas3().add(concursoMegaSenas3);
		concursoMegaSenas3.setNumero3(this);

		return concursoMegaSenas3;
	}

	public ConcursoMegaSena removeConcursoMegaSenas3(ConcursoMegaSena concursoMegaSenas3) {
		getConcursoMegaSenas3().remove(concursoMegaSenas3);
		concursoMegaSenas3.setNumero3(null);

		return concursoMegaSenas3;
	}

	public List<ConcursoMegaSena> getConcursoMegaSenas4() {
		return this.concursoMegaSenas4;
	}

	public void setConcursoMegaSenas4(List<ConcursoMegaSena> concursoMegaSenas4) {
		this.concursoMegaSenas4 = concursoMegaSenas4;
	}

	public ConcursoMegaSena addConcursoMegaSenas4(ConcursoMegaSena concursoMegaSenas4) {
		getConcursoMegaSenas4().add(concursoMegaSenas4);
		concursoMegaSenas4.setNumero4(this);

		return concursoMegaSenas4;
	}

	public ConcursoMegaSena removeConcursoMegaSenas4(ConcursoMegaSena concursoMegaSenas4) {
		getConcursoMegaSenas4().remove(concursoMegaSenas4);
		concursoMegaSenas4.setNumero4(null);

		return concursoMegaSenas4;
	}

	public List<ConcursoMegaSena> getConcursoMegaSenas5() {
		return this.concursoMegaSenas5;
	}

	public void setConcursoMegaSenas5(List<ConcursoMegaSena> concursoMegaSenas5) {
		this.concursoMegaSenas5 = concursoMegaSenas5;
	}

	public ConcursoMegaSena addConcursoMegaSenas5(ConcursoMegaSena concursoMegaSenas5) {
		getConcursoMegaSenas5().add(concursoMegaSenas5);
		concursoMegaSenas5.setNumero5(this);

		return concursoMegaSenas5;
	}

	public ConcursoMegaSena removeConcursoMegaSenas5(ConcursoMegaSena concursoMegaSenas5) {
		getConcursoMegaSenas5().remove(concursoMegaSenas5);
		concursoMegaSenas5.setNumero5(null);

		return concursoMegaSenas5;
	}

	public List<ConcursoMegaSena> getConcursoMegaSenas6() {
		return this.concursoMegaSenas6;
	}

	public void setConcursoMegaSenas6(List<ConcursoMegaSena> concursoMegaSenas6) {
		this.concursoMegaSenas6 = concursoMegaSenas6;
	}

	public ConcursoMegaSena addConcursoMegaSenas6(ConcursoMegaSena concursoMegaSenas6) {
		getConcursoMegaSenas6().add(concursoMegaSenas6);
		concursoMegaSenas6.setNumero6(this);

		return concursoMegaSenas6;
	}

	public ConcursoMegaSena removeConcursoMegaSenas6(ConcursoMegaSena concursoMegaSenas6) {
		getConcursoMegaSenas6().remove(concursoMegaSenas6);
		concursoMegaSenas6.setNumero6(null);

		return concursoMegaSenas6;
	}

	public List<Palpite> getPalpites() {
		return this.palpites;
	}

	public void setPalpites(List<Palpite> palpites) {
		this.palpites = palpites;
	}

	public Palpite addPalpite(Palpite palpite) {
		getPalpites().add(palpite);
		palpite.setNumero(this);

		return palpite;
	}

	public Palpite removePalpite(Palpite palpite) {
		getPalpites().remove(palpite);
		palpite.setNumero(null);

		return palpite;
	}

}