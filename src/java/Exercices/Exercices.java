/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exercices;

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
@Table(name = "exercices")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exercices.findAll", query = "SELECT e FROM Exercices e"),
    @NamedQuery(name = "Exercices.findByIdExercice", query = "SELECT e FROM Exercices e WHERE e.idExercice = :idExercice"),
    @NamedQuery(name = "Exercices.findByNomExercice", query = "SELECT e FROM Exercices e WHERE e.nomExercice = :nomExercice"),
    @NamedQuery(name = "Exercices.findByDescription", query = "SELECT e FROM Exercices e WHERE e.description = :description"),
    @NamedQuery(name = "Exercices.findByDifficulte", query = "SELECT e FROM Exercices e WHERE e.difficulte = :difficulte")})
public class Exercices implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idExercice")
    private Integer idExercice;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nomExercice")
    private String nomExercice;
    @Size(max = 500)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "difficulte")
    private int difficulte;

    public Exercices() {
    }

    public Exercices(Integer idExercice) {
        this.idExercice = idExercice;
    }

    public Exercices(Integer idExercice, String nomExercice, int difficulte) {
        this.idExercice = idExercice;
        this.nomExercice = nomExercice;
        this.difficulte = difficulte;
    }

    public Integer getIdExercice() {
        return idExercice;
    }

    public void setIdExercice(Integer idExercice) {
        this.idExercice = idExercice;
    }

    public String getNomExercice() {
        return nomExercice;
    }

    public void setNomExercice(String nomExercice) {
        this.nomExercice = nomExercice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(int difficulte) {
        this.difficulte = difficulte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExercice != null ? idExercice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exercices)) {
            return false;
        }
        Exercices other = (Exercices) object;
        if ((this.idExercice == null && other.idExercice != null) || (this.idExercice != null && !this.idExercice.equals(other.idExercice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Exercices.Exercices[ idExercice=" + idExercice + " ]";
    }
    
}
