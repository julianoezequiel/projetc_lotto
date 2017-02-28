/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lotto.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juliano
 */
@Entity
@Table(name = "palpitenumero")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Palpitenumero.findAll", query = "SELECT p FROM Palpitenumero p")
    , @NamedQuery(name = "Palpitenumero.findByIdpalpite", query = "SELECT p FROM Palpitenumero p WHERE p.palpitenumeroPK.idpalpite = :idpalpite")
    , @NamedQuery(name = "Palpitenumero.findByIdnumero", query = "SELECT p FROM Palpitenumero p WHERE p.palpitenumeroPK.idnumero = :idnumero")
    , @NamedQuery(name = "Palpitenumero.findByOrdemsorteio", query = "SELECT p FROM Palpitenumero p WHERE p.ordemsorteio = :ordemsorteio")})
public class Palpitenumero implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PalpitenumeroPK palpitenumeroPK;
    @Column(name = "ordemsorteio")
    private Integer ordemsorteio;
    @JoinColumn(name = "idnumero", referencedColumnName = "idnumero", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Numero numero;
    @JoinColumn(name = "idpalpite", referencedColumnName = "idpalpite", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Palpite palpite;

    public Palpitenumero() {
    }

    public Palpitenumero(PalpitenumeroPK palpitenumeroPK) {
        this.palpitenumeroPK = palpitenumeroPK;
    }

    public Palpitenumero(int idpalpite, int idnumero) {
        this.palpitenumeroPK = new PalpitenumeroPK(idpalpite, idnumero);
    }

    public PalpitenumeroPK getPalpitenumeroPK() {
        return palpitenumeroPK;
    }

    public void setPalpitenumeroPK(PalpitenumeroPK palpitenumeroPK) {
        this.palpitenumeroPK = palpitenumeroPK;
    }

    public Integer getOrdemsorteio() {
        return ordemsorteio;
    }

    public void setOrdemsorteio(Integer ordemsorteio) {
        this.ordemsorteio = ordemsorteio;
    }

    public Numero getNumero() {
        return numero;
    }

    public void setNumero(Numero numero) {
        this.numero = numero;
    }

    public Palpite getPalpite() {
        return palpite;
    }

    public void setPalpite(Palpite palpite) {
        this.palpite = palpite;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (palpitenumeroPK != null ? palpitenumeroPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Palpitenumero)) {
            return false;
        }
        Palpitenumero other = (Palpitenumero) object;
        if ((this.palpitenumeroPK == null && other.palpitenumeroPK != null) || (this.palpitenumeroPK != null && !this.palpitenumeroPK.equals(other.palpitenumeroPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.lotto.entity.Palpitenumero[ palpitenumeroPK=" + palpitenumeroPK + " ]";
    }
    
}
