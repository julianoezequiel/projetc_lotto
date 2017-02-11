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
@Table(name = "configuracoes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Configuracoes.findAll", query = "SELECT c FROM Configuracoes c")
    , @NamedQuery(name = "Configuracoes.findByIdConfiguracoes", query = "SELECT c FROM Configuracoes c WHERE c.idConfiguracoes = :idConfiguracoes")})
public class Configuracoes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idConfiguracoes")
    private Integer idConfiguracoes;

    public Configuracoes() {
    }

    public Configuracoes(Integer idConfiguracoes) {
        this.idConfiguracoes = idConfiguracoes;
    }

    public Integer getIdConfiguracoes() {
        return idConfiguracoes;
    }

    public void setIdConfiguracoes(Integer idConfiguracoes) {
        this.idConfiguracoes = idConfiguracoes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConfiguracoes != null ? idConfiguracoes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Configuracoes)) {
            return false;
        }
        Configuracoes other = (Configuracoes) object;
        if ((this.idConfiguracoes == null && other.idConfiguracoes != null) || (this.idConfiguracoes != null && !this.idConfiguracoes.equals(other.idConfiguracoes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.lotto.entity.Configuracoes[ idConfiguracoes=" + idConfiguracoes + " ]";
    }
    
}
