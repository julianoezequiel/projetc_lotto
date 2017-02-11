/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lotto.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juliano
 */
@Entity
@Table(name = "palpite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Palpite.findAll", query = "SELECT p FROM Palpite p")
    , @NamedQuery(name = "Palpite.findByIdPalpite", query = "SELECT p FROM Palpite p WHERE p.idPalpite = :idPalpite")})
public class Palpite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPalpite")
    private Integer idPalpite;
    @JoinColumn(name = "Numero_idNumero", referencedColumnName = "idNumero")
    @ManyToOne(optional = false)
    private Numero numeroidNumero;

    public Palpite() {
    }

    public Palpite(Integer idPalpite) {
        this.idPalpite = idPalpite;
    }

    public Integer getIdPalpite() {
        return idPalpite;
    }

    public void setIdPalpite(Integer idPalpite) {
        this.idPalpite = idPalpite;
    }

    public Numero getNumeroidNumero() {
        return numeroidNumero;
    }

    public void setNumeroidNumero(Numero numeroidNumero) {
        this.numeroidNumero = numeroidNumero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPalpite != null ? idPalpite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Palpite)) {
            return false;
        }
        Palpite other = (Palpite) object;
        if ((this.idPalpite == null && other.idPalpite != null) || (this.idPalpite != null && !this.idPalpite.equals(other.idPalpite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.lotto.entity.Palpite[ idPalpite=" + idPalpite + " ]";
    }
    
}
