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
public class PalpitenumeroPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idpalpite")
    private int idpalpite;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idnumero")
    private int idnumero;

    public PalpitenumeroPK() {
    }

    public PalpitenumeroPK(int idpalpite, int idnumero) {
        this.idpalpite = idpalpite;
        this.idnumero = idnumero;
    }

    public int getIdpalpite() {
        return idpalpite;
    }

    public void setIdpalpite(int idpalpite) {
        this.idpalpite = idpalpite;
    }

    public int getIdnumero() {
        return idnumero;
    }

    public void setIdnumero(int idnumero) {
        this.idnumero = idnumero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idpalpite;
        hash += (int) idnumero;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PalpitenumeroPK)) {
            return false;
        }
        PalpitenumeroPK other = (PalpitenumeroPK) object;
        if (this.idpalpite != other.idpalpite) {
            return false;
        }
        if (this.idnumero != other.idnumero) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.lotto.entity.PalpitenumeroPK[ idpalpite=" + idpalpite + ", idnumero=" + idnumero + " ]";
    }
    
}
