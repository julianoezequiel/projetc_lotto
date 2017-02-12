package model;

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
	private int idNumero;
	private String descricao;
	private String extenso;
	private int num;
	private List<Lotofacil> lotofacils1;
	private List<Lotofacil> lotofacils2;
	private List<Lotofacil> lotofacils3;
	private List<Lotofacil> lotofacils4;
	private List<Lotofacil> lotofacils5;
	private List<Lotofacil> lotofacils6;
	private List<Lotofacil> lotofacils7;
	private List<Lotofacil> lotofacils8;
	private List<Lotofacil> lotofacils9;
	private List<Lotofacil> lotofacils10;
	private List<Lotofacil> lotofacils11;
	private List<Lotofacil> lotofacils12;
	private List<Lotofacil> lotofacils13;
	private List<Lotofacil> lotofacils14;
	private List<Lotofacil> lotofacils15;
	private List<Megasena> megasenas1;
	private List<Megasena> megasenas2;
	private List<Megasena> megasenas3;
	private List<Megasena> megasenas4;
	private List<Megasena> megasenas5;
	private List<Megasena> megasenas6;
	private List<Palpite> palpites;

	public Numero() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_numero")
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


	//bi-directional many-to-one association to Lotofacil
	@OneToMany(mappedBy="numero1")
	public List<Lotofacil> getLotofacils1() {
		return this.lotofacils1;
	}

	public void setLotofacils1(List<Lotofacil> lotofacils1) {
		this.lotofacils1 = lotofacils1;
	}

	public Lotofacil addLotofacils1(Lotofacil lotofacils1) {
		getLotofacils1().add(lotofacils1);
		lotofacils1.setNumero1(this);

		return lotofacils1;
	}

	public Lotofacil removeLotofacils1(Lotofacil lotofacils1) {
		getLotofacils1().remove(lotofacils1);
		lotofacils1.setNumero1(null);

		return lotofacils1;
	}


	//bi-directional many-to-one association to Lotofacil
	@OneToMany(mappedBy="numero2")
	public List<Lotofacil> getLotofacils2() {
		return this.lotofacils2;
	}

	public void setLotofacils2(List<Lotofacil> lotofacils2) {
		this.lotofacils2 = lotofacils2;
	}

	public Lotofacil addLotofacils2(Lotofacil lotofacils2) {
		getLotofacils2().add(lotofacils2);
		lotofacils2.setNumero2(this);

		return lotofacils2;
	}

	public Lotofacil removeLotofacils2(Lotofacil lotofacils2) {
		getLotofacils2().remove(lotofacils2);
		lotofacils2.setNumero2(null);

		return lotofacils2;
	}


	//bi-directional many-to-one association to Lotofacil
	@OneToMany(mappedBy="numero3")
	public List<Lotofacil> getLotofacils3() {
		return this.lotofacils3;
	}

	public void setLotofacils3(List<Lotofacil> lotofacils3) {
		this.lotofacils3 = lotofacils3;
	}

	public Lotofacil addLotofacils3(Lotofacil lotofacils3) {
		getLotofacils3().add(lotofacils3);
		lotofacils3.setNumero3(this);

		return lotofacils3;
	}

	public Lotofacil removeLotofacils3(Lotofacil lotofacils3) {
		getLotofacils3().remove(lotofacils3);
		lotofacils3.setNumero3(null);

		return lotofacils3;
	}


	//bi-directional many-to-one association to Lotofacil
	@OneToMany(mappedBy="numero4")
	public List<Lotofacil> getLotofacils4() {
		return this.lotofacils4;
	}

	public void setLotofacils4(List<Lotofacil> lotofacils4) {
		this.lotofacils4 = lotofacils4;
	}

	public Lotofacil addLotofacils4(Lotofacil lotofacils4) {
		getLotofacils4().add(lotofacils4);
		lotofacils4.setNumero4(this);

		return lotofacils4;
	}

	public Lotofacil removeLotofacils4(Lotofacil lotofacils4) {
		getLotofacils4().remove(lotofacils4);
		lotofacils4.setNumero4(null);

		return lotofacils4;
	}


	//bi-directional many-to-one association to Lotofacil
	@OneToMany(mappedBy="numero5")
	public List<Lotofacil> getLotofacils5() {
		return this.lotofacils5;
	}

	public void setLotofacils5(List<Lotofacil> lotofacils5) {
		this.lotofacils5 = lotofacils5;
	}

	public Lotofacil addLotofacils5(Lotofacil lotofacils5) {
		getLotofacils5().add(lotofacils5);
		lotofacils5.setNumero5(this);

		return lotofacils5;
	}

	public Lotofacil removeLotofacils5(Lotofacil lotofacils5) {
		getLotofacils5().remove(lotofacils5);
		lotofacils5.setNumero5(null);

		return lotofacils5;
	}


	//bi-directional many-to-one association to Lotofacil
	@OneToMany(mappedBy="numero6")
	public List<Lotofacil> getLotofacils6() {
		return this.lotofacils6;
	}

	public void setLotofacils6(List<Lotofacil> lotofacils6) {
		this.lotofacils6 = lotofacils6;
	}

	public Lotofacil addLotofacils6(Lotofacil lotofacils6) {
		getLotofacils6().add(lotofacils6);
		lotofacils6.setNumero6(this);

		return lotofacils6;
	}

	public Lotofacil removeLotofacils6(Lotofacil lotofacils6) {
		getLotofacils6().remove(lotofacils6);
		lotofacils6.setNumero6(null);

		return lotofacils6;
	}


	//bi-directional many-to-one association to Lotofacil
	@OneToMany(mappedBy="numero7")
	public List<Lotofacil> getLotofacils7() {
		return this.lotofacils7;
	}

	public void setLotofacils7(List<Lotofacil> lotofacils7) {
		this.lotofacils7 = lotofacils7;
	}

	public Lotofacil addLotofacils7(Lotofacil lotofacils7) {
		getLotofacils7().add(lotofacils7);
		lotofacils7.setNumero7(this);

		return lotofacils7;
	}

	public Lotofacil removeLotofacils7(Lotofacil lotofacils7) {
		getLotofacils7().remove(lotofacils7);
		lotofacils7.setNumero7(null);

		return lotofacils7;
	}


	//bi-directional many-to-one association to Lotofacil
	@OneToMany(mappedBy="numero8")
	public List<Lotofacil> getLotofacils8() {
		return this.lotofacils8;
	}

	public void setLotofacils8(List<Lotofacil> lotofacils8) {
		this.lotofacils8 = lotofacils8;
	}

	public Lotofacil addLotofacils8(Lotofacil lotofacils8) {
		getLotofacils8().add(lotofacils8);
		lotofacils8.setNumero8(this);

		return lotofacils8;
	}

	public Lotofacil removeLotofacils8(Lotofacil lotofacils8) {
		getLotofacils8().remove(lotofacils8);
		lotofacils8.setNumero8(null);

		return lotofacils8;
	}


	//bi-directional many-to-one association to Lotofacil
	@OneToMany(mappedBy="numero9")
	public List<Lotofacil> getLotofacils9() {
		return this.lotofacils9;
	}

	public void setLotofacils9(List<Lotofacil> lotofacils9) {
		this.lotofacils9 = lotofacils9;
	}

	public Lotofacil addLotofacils9(Lotofacil lotofacils9) {
		getLotofacils9().add(lotofacils9);
		lotofacils9.setNumero9(this);

		return lotofacils9;
	}

	public Lotofacil removeLotofacils9(Lotofacil lotofacils9) {
		getLotofacils9().remove(lotofacils9);
		lotofacils9.setNumero9(null);

		return lotofacils9;
	}


	//bi-directional many-to-one association to Lotofacil
	@OneToMany(mappedBy="numero10")
	public List<Lotofacil> getLotofacils10() {
		return this.lotofacils10;
	}

	public void setLotofacils10(List<Lotofacil> lotofacils10) {
		this.lotofacils10 = lotofacils10;
	}

	public Lotofacil addLotofacils10(Lotofacil lotofacils10) {
		getLotofacils10().add(lotofacils10);
		lotofacils10.setNumero10(this);

		return lotofacils10;
	}

	public Lotofacil removeLotofacils10(Lotofacil lotofacils10) {
		getLotofacils10().remove(lotofacils10);
		lotofacils10.setNumero10(null);

		return lotofacils10;
	}


	//bi-directional many-to-one association to Lotofacil
	@OneToMany(mappedBy="numero11")
	public List<Lotofacil> getLotofacils11() {
		return this.lotofacils11;
	}

	public void setLotofacils11(List<Lotofacil> lotofacils11) {
		this.lotofacils11 = lotofacils11;
	}

	public Lotofacil addLotofacils11(Lotofacil lotofacils11) {
		getLotofacils11().add(lotofacils11);
		lotofacils11.setNumero11(this);

		return lotofacils11;
	}

	public Lotofacil removeLotofacils11(Lotofacil lotofacils11) {
		getLotofacils11().remove(lotofacils11);
		lotofacils11.setNumero11(null);

		return lotofacils11;
	}


	//bi-directional many-to-one association to Lotofacil
	@OneToMany(mappedBy="numero12")
	public List<Lotofacil> getLotofacils12() {
		return this.lotofacils12;
	}

	public void setLotofacils12(List<Lotofacil> lotofacils12) {
		this.lotofacils12 = lotofacils12;
	}

	public Lotofacil addLotofacils12(Lotofacil lotofacils12) {
		getLotofacils12().add(lotofacils12);
		lotofacils12.setNumero12(this);

		return lotofacils12;
	}

	public Lotofacil removeLotofacils12(Lotofacil lotofacils12) {
		getLotofacils12().remove(lotofacils12);
		lotofacils12.setNumero12(null);

		return lotofacils12;
	}


	//bi-directional many-to-one association to Lotofacil
	@OneToMany(mappedBy="numero13")
	public List<Lotofacil> getLotofacils13() {
		return this.lotofacils13;
	}

	public void setLotofacils13(List<Lotofacil> lotofacils13) {
		this.lotofacils13 = lotofacils13;
	}

	public Lotofacil addLotofacils13(Lotofacil lotofacils13) {
		getLotofacils13().add(lotofacils13);
		lotofacils13.setNumero13(this);

		return lotofacils13;
	}

	public Lotofacil removeLotofacils13(Lotofacil lotofacils13) {
		getLotofacils13().remove(lotofacils13);
		lotofacils13.setNumero13(null);

		return lotofacils13;
	}


	//bi-directional many-to-one association to Lotofacil
	@OneToMany(mappedBy="numero14")
	public List<Lotofacil> getLotofacils14() {
		return this.lotofacils14;
	}

	public void setLotofacils14(List<Lotofacil> lotofacils14) {
		this.lotofacils14 = lotofacils14;
	}

	public Lotofacil addLotofacils14(Lotofacil lotofacils14) {
		getLotofacils14().add(lotofacils14);
		lotofacils14.setNumero14(this);

		return lotofacils14;
	}

	public Lotofacil removeLotofacils14(Lotofacil lotofacils14) {
		getLotofacils14().remove(lotofacils14);
		lotofacils14.setNumero14(null);

		return lotofacils14;
	}


	//bi-directional many-to-one association to Lotofacil
	@OneToMany(mappedBy="numero15")
	public List<Lotofacil> getLotofacils15() {
		return this.lotofacils15;
	}

	public void setLotofacils15(List<Lotofacil> lotofacils15) {
		this.lotofacils15 = lotofacils15;
	}

	public Lotofacil addLotofacils15(Lotofacil lotofacils15) {
		getLotofacils15().add(lotofacils15);
		lotofacils15.setNumero15(this);

		return lotofacils15;
	}

	public Lotofacil removeLotofacils15(Lotofacil lotofacils15) {
		getLotofacils15().remove(lotofacils15);
		lotofacils15.setNumero15(null);

		return lotofacils15;
	}


	//bi-directional many-to-one association to Megasena
	@OneToMany(mappedBy="numero1")
	public List<Megasena> getMegasenas1() {
		return this.megasenas1;
	}

	public void setMegasenas1(List<Megasena> megasenas1) {
		this.megasenas1 = megasenas1;
	}

	public Megasena addMegasenas1(Megasena megasenas1) {
		getMegasenas1().add(megasenas1);
		megasenas1.setNumero1(this);

		return megasenas1;
	}

	public Megasena removeMegasenas1(Megasena megasenas1) {
		getMegasenas1().remove(megasenas1);
		megasenas1.setNumero1(null);

		return megasenas1;
	}


	//bi-directional many-to-one association to Megasena
	@OneToMany(mappedBy="numero2")
	public List<Megasena> getMegasenas2() {
		return this.megasenas2;
	}

	public void setMegasenas2(List<Megasena> megasenas2) {
		this.megasenas2 = megasenas2;
	}

	public Megasena addMegasenas2(Megasena megasenas2) {
		getMegasenas2().add(megasenas2);
		megasenas2.setNumero2(this);

		return megasenas2;
	}

	public Megasena removeMegasenas2(Megasena megasenas2) {
		getMegasenas2().remove(megasenas2);
		megasenas2.setNumero2(null);

		return megasenas2;
	}


	//bi-directional many-to-one association to Megasena
	@OneToMany(mappedBy="numero3")
	public List<Megasena> getMegasenas3() {
		return this.megasenas3;
	}

	public void setMegasenas3(List<Megasena> megasenas3) {
		this.megasenas3 = megasenas3;
	}

	public Megasena addMegasenas3(Megasena megasenas3) {
		getMegasenas3().add(megasenas3);
		megasenas3.setNumero3(this);

		return megasenas3;
	}

	public Megasena removeMegasenas3(Megasena megasenas3) {
		getMegasenas3().remove(megasenas3);
		megasenas3.setNumero3(null);

		return megasenas3;
	}


	//bi-directional many-to-one association to Megasena
	@OneToMany(mappedBy="numero4")
	public List<Megasena> getMegasenas4() {
		return this.megasenas4;
	}

	public void setMegasenas4(List<Megasena> megasenas4) {
		this.megasenas4 = megasenas4;
	}

	public Megasena addMegasenas4(Megasena megasenas4) {
		getMegasenas4().add(megasenas4);
		megasenas4.setNumero4(this);

		return megasenas4;
	}

	public Megasena removeMegasenas4(Megasena megasenas4) {
		getMegasenas4().remove(megasenas4);
		megasenas4.setNumero4(null);

		return megasenas4;
	}


	//bi-directional many-to-one association to Megasena
	@OneToMany(mappedBy="numero5")
	public List<Megasena> getMegasenas5() {
		return this.megasenas5;
	}

	public void setMegasenas5(List<Megasena> megasenas5) {
		this.megasenas5 = megasenas5;
	}

	public Megasena addMegasenas5(Megasena megasenas5) {
		getMegasenas5().add(megasenas5);
		megasenas5.setNumero5(this);

		return megasenas5;
	}

	public Megasena removeMegasenas5(Megasena megasenas5) {
		getMegasenas5().remove(megasenas5);
		megasenas5.setNumero5(null);

		return megasenas5;
	}


	//bi-directional many-to-one association to Megasena
	@OneToMany(mappedBy="numero6")
	public List<Megasena> getMegasenas6() {
		return this.megasenas6;
	}

	public void setMegasenas6(List<Megasena> megasenas6) {
		this.megasenas6 = megasenas6;
	}

	public Megasena addMegasenas6(Megasena megasenas6) {
		getMegasenas6().add(megasenas6);
		megasenas6.setNumero6(this);

		return megasenas6;
	}

	public Megasena removeMegasenas6(Megasena megasenas6) {
		getMegasenas6().remove(megasenas6);
		megasenas6.setNumero6(null);

		return megasenas6;
	}


	//bi-directional many-to-one association to Palpite
	@OneToMany(mappedBy="numero")
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