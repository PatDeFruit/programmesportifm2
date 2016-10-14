/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Niveaux;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "niveaux")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Niveaux.findAll", query = "SELECT n FROM Niveaux n"),
    @NamedQuery(name = "Niveaux.findByIdNiveau", query = "SELECT n FROM Niveaux n WHERE n.idNiveau = :idNiveau"),
    @NamedQuery(name = "Niveaux.findByNomNiveau", query = "SELECT n FROM Niveaux n WHERE n.nomNiveau = :nomNiveau")})
public class Niveaux implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idNiveau")
    private Integer idNiveau;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nomNiveau")
    private String nomNiveau;

    public Niveaux() {
    }

    public Niveaux(Integer idNiveau) {
        this.idNiveau = idNiveau;
    }

    public Niveaux(Integer idNiveau, String nomNiveau) {
        this.idNiveau = idNiveau;
        this.nomNiveau = nomNiveau;
    }

    public Integer getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(Integer idNiveau) {
        this.idNiveau = idNiveau;
    }

    public String getNomNiveau() {
        return nomNiveau;
    }

    public void setNomNiveau(String nomNiveau) {
        this.nomNiveau = nomNiveau;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNiveau != null ? idNiveau.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Niveaux)) {
            return false;
        }
        Niveaux other = (Niveaux) object;
        if ((this.idNiveau == null && other.idNiveau != null) || (this.idNiveau != null && !this.idNiveau.equals(other.idNiveau))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Niveaux.Niveaux[ idNiveau=" + idNiveau + " ]";
    }
    
}
