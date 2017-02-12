package br.com.lotto.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the palpite database table.
 * 
 */
@Entity
@NamedQuery(name="Palpite.findAll", query="SELECT p FROM Palpite p")
public class Palpite implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idPalpite;
	private Lotofacil lotofacil;
	private Megasena megasena;
	private Numero numero;

	public Palpite() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIdPalpite() {
		return this.idPalpite;
	}

	public void setIdPalpite(int idPalpite) {
		this.idPalpite = idPalpite;
	}


	//bi-directional many-to-one association to Lotofacil
	@ManyToOne
	@JoinColumn(name="LotoFacil_idConcurso_Loto_Facil")
	public Lotofacil getLotofacil() {
		return this.lotofacil;
	}

	public void setLotofacil(Lotofacil lotofacil) {
		this.lotofacil = lotofacil;
	}


	//bi-directional many-to-one association to Megasena
	@ManyToOne
	public Megasena getMegasena() {
		return this.megasena;
	}

	public void setMegasena(Megasena megasena) {
		this.megasena = megasena;
	}


	//bi-directional many-to-one association to Numero
	@ManyToOne
	@JoinColumn(name="Numero_idNumero")
	public Numero getNumero() {
		return this.numero;
	}

	public void setNumero(Numero numero) {
		this.numero = numero;
	}

}