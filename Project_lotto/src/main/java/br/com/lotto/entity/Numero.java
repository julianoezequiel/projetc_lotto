/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lotto.entity;

import br.com.lotto.dto.NumeroDTO;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juliano
 */
@Entity
@Table(name = "numero")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Numero.findAll", query = "SELECT n FROM Numero n")
    , @NamedQuery(name = "Numero.findByIdnumero", query = "SELECT n FROM Numero n WHERE n.idnumero = :idnumero")
    , @NamedQuery(name = "Numero.findByDescricao", query = "SELECT n FROM Numero n WHERE n.descricao = :descricao")
    , @NamedQuery(name = "Numero.findByNum", query = "SELECT n FROM Numero n WHERE n.num = :num")
    , @NamedQuery(name = "Numero.findByExtenso", query = "SELECT n FROM Numero n WHERE n.extenso = :extenso")})
public class Numero implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idnumero")
    private Integer idnumero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num")
    private int num;
    @Size(max = 200)
    @Column(name = "extenso")
    private String extenso;
    @ManyToMany(mappedBy = "numeroCollection")
    private Collection<Megasena> megasenaCollection;
    @ManyToMany(mappedBy = "numeroCollection")
    private Collection<Lotofacil> lotofacilCollection;
    @OneToMany(mappedBy = "numeroidnumero")
    private Collection<Palpite> palpiteCollection;

    public Numero() {
    }

    public Numero(Integer idnumero) {
        this.idnumero = idnumero;
    }

    public Numero(Integer idnumero, String descricao, int num) {
        this.idnumero = idnumero;
        this.descricao = descricao;
        this.num = num;
    }

    public Integer getIdnumero() {
        return idnumero;
    }

    public void setIdnumero(Integer idnumero) {
        this.idnumero = idnumero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getExtenso() {
        return extenso;
    }

    public void setExtenso(String extenso) {
        this.extenso = extenso;
    }

    @XmlTransient
    public Collection<Megasena> getMegasenaCollection() {
        return megasenaCollection;
    }

    public void setMegasenaCollection(Collection<Megasena> megasenaCollection) {
        this.megasenaCollection = megasenaCollection;
    }

    @XmlTransient
    public Collection<Lotofacil> getLotofacilCollection() {
        return lotofacilCollection;
    }

    public void setLotofacilCollection(Collection<Lotofacil> lotofacilCollection) {
        this.lotofacilCollection = lotofacilCollection;
    }

    @XmlTransient
    public Collection<Palpite> getPalpiteCollection() {
        return palpiteCollection;
    }

    public void setPalpiteCollection(Collection<Palpite> palpiteCollection) {
        this.palpiteCollection = palpiteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnumero != null ? idnumero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Numero)) {
            return false;
        }
        Numero other = (Numero) object;
        if ((this.idnumero == null && other.idnumero != null) || (this.idnumero != null && !this.idnumero.equals(other.idnumero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.lotto.entity.Numero[ idnumero=" + idnumero + " ]";
    }
    
     public NumeroDTO toNumeroDTO(){
        NumeroDTO dto = new NumeroDTO(this.idnumero);
        return dto;
    }
}
