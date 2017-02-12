package br.com.lotto.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tipo database table.
 * 
 */
@Entity
@NamedQuery(name="Tipo.findAll", query="SELECT t FROM Tipo t")
public class Tipo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idTipo;
	private String descricao;
	private Megasena megasena;

	public Tipo() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_tipo")
	public int getIdTipo() {
		return this.idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}


	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	//bi-directional many-to-one association to Megasena
	@ManyToOne
	@JoinColumn(name="Concurso_idConcurso")
	public Megasena getMegasena() {
		return this.megasena;
	}

	public void setMegasena(Megasena megasena) {
		this.megasena = megasena;
	}

}