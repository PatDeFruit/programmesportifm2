/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Programmes;

import Entrainements.Entrainements;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author Asus
 */
@Entity
@Table(name = "programmes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Programmes.findAll", query = "SELECT p FROM Programmes p order by p.idProgramme"),
    @NamedQuery(name = "Programmes.findByIdProgramme", query = "SELECT p FROM Programmes p WHERE p.idProgramme = :idProgramme"),
    @NamedQuery(name = "Programmes.findByNomProgramme", query = "SELECT p FROM Programmes p WHERE p.nomProgramme = :nomProgramme"),
    @NamedQuery(name = "Programmes.findByObjectif", query = "SELECT p FROM Programmes p WHERE p.objectif = :objectif")})
public class Programmes implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProgramme")
    private Collection<Entrainements> entrainementsCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProgramme")
    private Integer idProgramme;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nomProgramme")
    private String nomProgramme;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "objectif")
    private String objectif;

    public Programmes() {
    }

    public Programmes(Integer idProgramme) {
        this.idProgramme = idProgramme;
    }

    public Programmes(Integer idProgramme, String nomProgramme, String objectif) {
        this.idProgramme = idProgramme;
        this.nomProgramme = nomProgramme;
        this.objectif = objectif;
    }

    public Integer getIdProgramme() {
        return idProgramme;
    }

    public void setIdProgramme(Integer idProgramme) {
        this.idProgramme = idProgramme;
    }

    public String getNomProgramme() {
        return nomProgramme;
    }

    public void setNomProgramme(String nomProgramme) {
        this.nomProgramme = nomProgramme;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProgramme != null ? idProgramme.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programmes)) {
            return false;
        }
        Programmes other = (Programmes) object;
        if ((this.idProgramme == null && other.idProgramme != null) || (this.idProgramme != null && !this.idProgramme.equals(other.idProgramme))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Programmes.Programmes[ idProgramme=" + idProgramme + " ]";
    }

    @XmlTransient
    public Collection<Entrainements> getEntrainementsCollection() {
        return entrainementsCollection;
    }

    public void setEntrainementsCollection(Collection<Entrainements> entrainementsCollection) {
        this.entrainementsCollection = entrainementsCollection;
    }
    
}
