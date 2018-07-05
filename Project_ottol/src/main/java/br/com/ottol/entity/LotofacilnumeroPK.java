/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ottol.entity;

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
public class LotofacilnumeroPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "lotoFacillidlotofacil")
    private int lotoFacillidlotofacil;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numeroidnumero")
    private int numeroidnumero;

    public LotofacilnumeroPK() {
    }

    public LotofacilnumeroPK(int lotoFacillidlotofacil, int numeroidnumero) {
        this.lotoFacillidlotofacil = lotoFacillidlotofacil;
        this.numeroidnumero = numeroidnumero;
    }

    public int getLotoFacillidlotofacil() {
        return lotoFacillidlotofacil;
    }

    public void setLotoFacillidlotofacil(int lotoFacillidlotofacil) {
        this.lotoFacillidlotofacil = lotoFacillidlotofacil;
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
        hash += (int) lotoFacillidlotofacil;
        hash += (int) numeroidnumero;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LotofacilnumeroPK)) {
            return false;
        }
        LotofacilnumeroPK other = (LotofacilnumeroPK) object;
        if (this.lotoFacillidlotofacil != other.lotoFacillidlotofacil) {
            return false;
        }
        if (this.numeroidnumero != other.numeroidnumero) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.lotto.entity.LotofacilnumeroPK[ lotoFacillidlotofacil=" + lotoFacillidlotofacil + ", numeroidnumero=" + numeroidnumero + " ]";
    }
    
}
