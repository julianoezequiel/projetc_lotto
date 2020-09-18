/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ot.entity;

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
@Table(name = "lotofacilnumero")
@XmlRootElement
@NamedQueries({
   @NamedQuery(name = "Lotofacilnumero.findAll", query = "SELECT l FROM Lotofacilnumero l")
   , @NamedQuery(name = "Lotofacilnumero.findByLotoFacillidlotofacil", query = "SELECT l FROM Lotofacilnumero l WHERE l.lotofacilnumeroPK.lotoFacillidlotofacil = :lotoFacillidlotofacil")
   , @NamedQuery(name = "Lotofacilnumero.findByNumeroidnumero", query = "SELECT l FROM Lotofacilnumero l WHERE l.lotofacilnumeroPK.numeroidnumero = :numeroidnumero")
   , @NamedQuery(name = "Lotofacilnumero.findByOrdemsorteio", query = "SELECT l FROM Lotofacilnumero l WHERE l.ordemsorteio = :ordemsorteio")})
public class Lotofacilnumero implements Serializable {

   private static final long serialVersionUID = 1L;
   @EmbeddedId
   protected LotofacilnumeroPK lotofacilnumeroPK;
   @Column(name = "ordemsorteio")
   private Integer ordemsorteio;
   @JoinColumn(name = "lotoFacillidlotofacil", referencedColumnName = "idlotofacil", insertable = false, updatable = false)
   @ManyToOne(optional = false)
   private Lotofacil lotofacil;
   @JoinColumn(name = "numeroidnumero", referencedColumnName = "idnumero", insertable = false, updatable = false)
   @ManyToOne(optional = false)
   private Numero numero;

   public Lotofacilnumero() {
   }

   public Lotofacilnumero(LotofacilnumeroPK lotofacilnumeroPK) {
       this.lotofacilnumeroPK = lotofacilnumeroPK;
   }

   public Lotofacilnumero(int lotoFacillidlotofacil, int numeroidnumero) {
       this.lotofacilnumeroPK = new LotofacilnumeroPK(lotoFacillidlotofacil, numeroidnumero);
   }

   public LotofacilnumeroPK getLotofacilnumeroPK() {
       return lotofacilnumeroPK;
   }

   public void setLotofacilnumeroPK(LotofacilnumeroPK lotofacilnumeroPK) {
       this.lotofacilnumeroPK = lotofacilnumeroPK;
   }

   public Integer getOrdemsorteio() {
       return ordemsorteio;
   }

   public void setOrdemsorteio(Integer ordemsorteio) {
       this.ordemsorteio = ordemsorteio;
   }

   public Lotofacil getLotofacil() {
       return lotofacil;
   }

   public void setLotofacil(Lotofacil lotofacil) {
       this.lotofacil = lotofacil;
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
       hash += (lotofacilnumeroPK != null ? lotofacilnumeroPK.hashCode() : 0);
       return hash;
   }

   @Override
   public boolean equals(Object object) {
       // TODO: Warning - this method won't work in the case the id fields are not set
       if (!(object instanceof Lotofacilnumero)) {
           return false;
       }
       Lotofacilnumero other = (Lotofacilnumero) object;
       if ((this.lotofacilnumeroPK == null && other.lotofacilnumeroPK != null) || (this.lotofacilnumeroPK != null && !this.lotofacilnumeroPK.equals(other.lotofacilnumeroPK))) {
           return false;
       }
       return true;
   }

   @Override
   public String toString() {
       return "br.com.lotto.entity.Lotofacilnumero[ lotofacilnumeroPK=" + lotofacilnumeroPK + " ]";
   }
   
}
