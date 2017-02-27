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
@Table(name = "megasenanumero")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Megasenanumero.findAll", query = "SELECT m FROM Megasenanumero m")
    , @NamedQuery(name = "Megasenanumero.findByMegasenaidconcurso", query = "SELECT m FROM Megasenanumero m WHERE m.megasenanumeroPK.megasenaidconcurso = :megasenaidconcurso")
    , @NamedQuery(name = "Megasenanumero.findByNumeroidnumero", query = "SELECT m FROM Megasenanumero m WHERE m.megasenanumeroPK.numeroidnumero = :numeroidnumero")
    , @NamedQuery(name = "Megasenanumero.findByOrdemsorteio", query = "SELECT m FROM Megasenanumero m WHERE m.ordemsorteio = :ordemsorteio")})
public class Megasenanumero implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MegasenanumeroPK megasenanumeroPK;
    @Column(name = "ordemsorteio")
    private Integer ordemsorteio;
    @JoinColumn(name = "megasenaidconcurso", referencedColumnName = "idconcurso", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Megasena megasena;
    @JoinColumn(name = "numeroidnumero", referencedColumnName = "idnumero", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Numero numero;

    public Megasenanumero() {
    }

    public Megasenanumero(MegasenanumeroPK megasenanumeroPK) {
        this.megasenanumeroPK = megasenanumeroPK;
    }

    public Megasenanumero(int megasenaidconcurso, int numeroidnumero) {
        this.megasenanumeroPK = new MegasenanumeroPK(megasenaidconcurso, numeroidnumero);
    }

    public MegasenanumeroPK getMegasenanumeroPK() {
        return megasenanumeroPK;
    }

    public void setMegasenanumeroPK(MegasenanumeroPK megasenanumeroPK) {
        this.megasenanumeroPK = megasenanumeroPK;
    }

    public Integer getOrdemsorteio() {
        return ordemsorteio;
    }

    public void setOrdemsorteio(Integer ordemsorteio) {
        this.ordemsorteio = ordemsorteio;
    }

    public Megasena getMegasena() {
        return megasena;
    }

    public void setMegasena(Megasena megasena) {
        this.megasena = megasena;
    }

    public Numero getNumero() {
        return numero;
    }

    public void setNumero(Numero numero) {
        this.numero = numero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (megasenanumeroPK != null ? megasenanumeroPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Megasenanumero)) {
            return false;
        }
        Megasenanumero other = (Megasenanumero) object;
        if ((this.megasenanumeroPK == null && other.megasenanumeroPK != null) || (this.megasenanumeroPK != null && !this.megasenanumeroPK.equals(other.megasenanumeroPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Numero : " + megasenanumeroPK ;
    }
    
}
