/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lotto.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Juliano
 */
@Embeddable
public class MegasenanumeroPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "megasenaidconcurso")
    private int megasenaidconcurso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numeroidnumero")
    private int numeroidnumero;

    public MegasenanumeroPK() {
    }

    public MegasenanumeroPK(int megasenaidconcurso, int numeroidnumero) {
        this.megasenaidconcurso = megasenaidconcurso;
        this.numeroidnumero = numeroidnumero;
    }

    public int getMegasenaidconcurso() {
        return megasenaidconcurso;
    }

    public void setMegasenaidconcurso(int megasenaidconcurso) {
        this.megasenaidconcurso = megasenaidconcurso;
    }

    public int getNumeroidnumero() {
        return numeroidnumero;
    }

    public void setNumeroidnumero(int numeroidnumero) {
        this.numeroidnumero = numeroidnumero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) megasenaidconcurso;
        hash += (int) numeroidnumero;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MegasenanumeroPK)) {
            return false;
        }
        MegasenanumeroPK other = (MegasenanumeroPK) object;
        if (this.megasenaidconcurso != other.megasenaidconcurso) {
            return false;
        }
        if (this.numeroidnumero != other.numeroidnumero) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.lotto.entity.MegasenanumeroPK[ megasenaidconcurso=" + megasenaidconcurso + ", numeroidnumero=" + numeroidnumero + " ]";
    }
    
}
