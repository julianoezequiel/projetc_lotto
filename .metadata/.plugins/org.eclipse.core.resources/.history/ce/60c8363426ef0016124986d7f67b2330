package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the concurso_loto_facil database table.
 * 
 */
@Embeddable
public class ConcursoLotoFacilPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idConcurso_Loto_Facil;

	@Column(insertable=false, updatable=false)
	private int numero_idNumero;

	public ConcursoLotoFacilPK() {
	}
	public int getIdConcurso_Loto_Facil() {
		return this.idConcurso_Loto_Facil;
	}
	public void setIdConcurso_Loto_Facil(int idConcurso_Loto_Facil) {
		this.idConcurso_Loto_Facil = idConcurso_Loto_Facil;
	}
	public int getNumero_idNumero() {
		return this.numero_idNumero;
	}
	public void setNumero_idNumero(int numero_idNumero) {
		this.numero_idNumero = numero_idNumero;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ConcursoLotoFacilPK)) {
			return false;
		}
		ConcursoLotoFacilPK castOther = (ConcursoLotoFacilPK)other;
		return 
			(this.idConcurso_Loto_Facil == castOther.idConcurso_Loto_Facil)
			&& (this.numero_idNumero == castOther.numero_idNumero);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idConcurso_Loto_Facil;
		hash = hash * prime + this.numero_idNumero;
		
		return hash;
	}
}