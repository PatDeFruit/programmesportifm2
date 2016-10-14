/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Constitue;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "est_constitue")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstConstitue.findAll", query = "SELECT e FROM EstConstitue e"),
    @NamedQuery(name = "EstConstitue.findByIdConstitue", query = "SELECT e FROM EstConstitue e WHERE e.idConstitue = :idConstitue")})
public class EstConstitue implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idConstitue")
    private Integer idConstitue;

    public EstConstitue() {
    }

    public EstConstitue(Integer idConstitue) {
        this.idConstitue = idConstitue;
    }

    public Integer getIdConstitue() {
        return idConstitue;
    }

    public void setIdConstitue(Integer idConstitue) {
        this.idConstitue = idConstitue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConstitue != null ? idConstitue.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstConstitue)) {
            return false;
        }
        EstConstitue other = (EstConstitue) object;
        if ((this.idConstitue == null && other.idConstitue != null) || (this.idConstitue != null && !this.idConstitue.equals(other.idConstitue))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Constitue.EstConstitue[ idConstitue=" + idConstitue + " ]";
    }
    
}
