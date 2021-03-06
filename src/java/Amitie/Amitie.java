/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Amitie;

import Comptes.Comptes;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "amitie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Amitie.findAll", query = "SELECT a FROM Amitie a"),
    @NamedQuery(name = "Amitie.findByIdAmitie", query = "SELECT a FROM Amitie a WHERE a.idAmitie = :idAmitie")})
public class Amitie implements Serializable {

    @JoinColumn(name = "login2", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Comptes login2;
    @JoinColumn(name = "login1", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Comptes login1;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAmitie")
    private Integer idAmitie;

    public Amitie() {
    }

    public Amitie(Integer idAmitie) {
        this.idAmitie = idAmitie;
    }

    public Integer getIdAmitie() {
        return idAmitie;
    }

    public void setIdAmitie(Integer idAmitie) {
        this.idAmitie = idAmitie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAmitie != null ? idAmitie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Amitie)) {
            return false;
        }
        Amitie other = (Amitie) object;
        if ((this.idAmitie == null && other.idAmitie != null) || (this.idAmitie != null && !this.idAmitie.equals(other.idAmitie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Amitie.Amitie[ idAmitie=" + idAmitie + " ]";
    }

    public Comptes getLogin2() {
        return login2;
    }

    public void setLogin2(Comptes login2) {
        this.login2 = login2;
    }

    public Comptes getLogin1() {
        return login1;
    }

    public void setLogin1(Comptes login1) {
        this.login1 = login1;
    }
    
}
