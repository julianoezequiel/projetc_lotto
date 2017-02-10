package model;

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

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idTipo;

	private String descricao;

	//bi-directional many-to-one association to ConcursoMegaSena
	@ManyToOne
	@JoinColumn(name="Concurso_idConcurso")
	private ConcursoMegaSena concursoMegaSena;

	public Tipo() {
	}

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

	public ConcursoMegaSena getConcursoMegaSena() {
		return this.concursoMegaSena;
	}

	public void setConcursoMegaSena(ConcursoMegaSena concursoMegaSena) {
		this.concursoMegaSena = concursoMegaSena;
	}

}