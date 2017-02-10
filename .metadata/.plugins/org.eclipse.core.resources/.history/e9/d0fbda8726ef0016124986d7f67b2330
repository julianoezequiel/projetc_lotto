package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the configuracoes database table.
 * 
 */
@Entity
@Table(name="configuracoes")
@NamedQuery(name="Configuracoe.findAll", query="SELECT c FROM Configuracoe c")
public class Configuracoe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idConfiguracoes;

	public Configuracoe() {
	}

	public int getIdConfiguracoes() {
		return this.idConfiguracoes;
	}

	public void setIdConfiguracoes(int idConfiguracoes) {
		this.idConfiguracoes = idConfiguracoes;
	}

}