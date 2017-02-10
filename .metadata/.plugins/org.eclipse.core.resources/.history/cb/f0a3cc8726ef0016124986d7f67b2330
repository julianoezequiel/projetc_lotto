package model;

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

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idPalpite;

	//bi-directional many-to-one association to Numero
	@ManyToOne
	private Numero numero;

	public Palpite() {
	}

	public int getIdPalpite() {
		return this.idPalpite;
	}

	public void setIdPalpite(int idPalpite) {
		this.idPalpite = idPalpite;
	}

	public Numero getNumero() {
		return this.numero;
	}

	public void setNumero(Numero numero) {
		this.numero = numero;
	}

}