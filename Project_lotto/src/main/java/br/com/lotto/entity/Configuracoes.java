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
    , @NamedQuery(name = "Configuracoes.findByIdconfiguracoes", query = "SELECT c FROM Configuracoes c WHERE c.idconfiguracoes = :idconfiguracoes")})
public class Configuracoes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idconfiguracoes")
    private Integer idconfiguracoes;

    public Configuracoes() {
    }

    public Configuracoes(Integer idconfiguracoes) {
        this.idconfiguracoes = idconfiguracoes;
    }

    public Integer getIdconfiguracoes() {
        return idconfiguracoes;
    }

    public void setIdconfiguracoes(Integer idconfiguracoes) {
        this.idconfiguracoes = idconfiguracoes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idconfiguracoes != null ? idconfiguracoes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Configuracoes)) {
            return false;
        }
        Configuracoes other = (Configuracoes) object;
        if ((this.idconfiguracoes == null && other.idconfiguracoes != null) || (this.idconfiguracoes != null && !this.idconfiguracoes.equals(other.idconfiguracoes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.lotto.entity.Configuracoes[ idconfiguracoes=" + idconfiguracoes + " ]";
    }
    
}
