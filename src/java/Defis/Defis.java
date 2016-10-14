/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Defis;

import Comptes.Comptes;
import Exercices.Exercices;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "defis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Defis.findAll", query = "SELECT d FROM Defis d"),
    @NamedQuery(name = "Defis.findByIdDefis", query = "SELECT d FROM Defis d WHERE d.idDefis = :idDefis"),
    @NamedQuery(name = "Defis.findByEffectue", query = "SELECT d FROM Defis d WHERE d.effectue = :effectue")})
public class Defis implements Serializable {

    @JoinColumn(name = "idExercice", referencedColumnName = "idExercice")
    @ManyToOne(optional = false)
    private Exercices idExercice;
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
    @Column(name = "idDefis")
    private Integer idDefis;
    @Basic(optional = false)
    @NotNull
    @Column(name = "effectue")
    private boolean effectue;

    public Defis() {
    }

    public Defis(Integer idDefis) {
        this.idDefis = idDefis;
    }

    public Defis(Integer idDefis, boolean effectue) {
        this.idDefis = idDefis;
        this.effectue = effectue;
    }

    public Integer getIdDefis() {
        return idDefis;
    }

    public void setIdDefis(Integer idDefis) {
        this.idDefis = idDefis;
    }

    public boolean getEffectue() {
        return effectue;
    }

    public void setEffectue(boolean effectue) {
        this.effectue = effectue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDefis != null ? idDefis.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Defis)) {
            return false;
        }
        Defis other = (Defis) object;
        if ((this.idDefis == null && other.idDefis != null) || (this.idDefis != null && !this.idDefis.equals(other.idDefis))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Defis.Defis[ idDefis=" + idDefis + " ]";
    }

    public Exercices getIdExercice() {
        return idExercice;
    }

    public void setIdExercice(Exercices idExercice) {
        this.idExercice = idExercice;
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
