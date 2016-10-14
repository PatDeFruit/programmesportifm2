/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entrainements;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "entrainements")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entrainements.findAll", query = "SELECT e FROM Entrainements e"),
    @NamedQuery(name = "Entrainements.findByIdEntrainements", query = "SELECT e FROM Entrainements e WHERE e.idEntrainements = :idEntrainements"),
    @NamedQuery(name = "Entrainements.findByDateEntrainement", query = "SELECT e FROM Entrainements e WHERE e.dateEntrainement = :dateEntrainement"),
    @NamedQuery(name = "Entrainements.findByNbRepetEffect", query = "SELECT e FROM Entrainements e WHERE e.nbRepetEffect = :nbRepetEffect"),
    @NamedQuery(name = "Entrainements.findByNbSerieEffect", query = "SELECT e FROM Entrainements e WHERE e.nbSerieEffect = :nbSerieEffect"),
    @NamedQuery(name = "Entrainements.findByTempsEffect", query = "SELECT e FROM Entrainements e WHERE e.tempsEffect = :tempsEffect")})
public class Entrainements implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEntrainements")
    private Integer idEntrainements;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateEntrainement")
    @Temporal(TemporalType.DATE)
    private Date dateEntrainement;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nbRepetEffect")
    private int nbRepetEffect;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nbSerieEffect")
    private int nbSerieEffect;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tempsEffect")
    private int tempsEffect;

    public Entrainements() {
    }

    public Entrainements(Integer idEntrainements) {
        this.idEntrainements = idEntrainements;
    }

    public Entrainements(Integer idEntrainements, Date dateEntrainement, int nbRepetEffect, int nbSerieEffect, int tempsEffect) {
        this.idEntrainements = idEntrainements;
        this.dateEntrainement = dateEntrainement;
        this.nbRepetEffect = nbRepetEffect;
        this.nbSerieEffect = nbSerieEffect;
        this.tempsEffect = tempsEffect;
    }

    public Integer getIdEntrainements() {
        return idEntrainements;
    }

    public void setIdEntrainements(Integer idEntrainements) {
        this.idEntrainements = idEntrainements;
    }

    public Date getDateEntrainement() {
        return dateEntrainement;
    }

    public void setDateEntrainement(Date dateEntrainement) {
        this.dateEntrainement = dateEntrainement;
    }

    public int getNbRepetEffect() {
        return nbRepetEffect;
    }

    public void setNbRepetEffect(int nbRepetEffect) {
        this.nbRepetEffect = nbRepetEffect;
    }

    public int getNbSerieEffect() {
        return nbSerieEffect;
    }

    public void setNbSerieEffect(int nbSerieEffect) {
        this.nbSerieEffect = nbSerieEffect;
    }

    public int getTempsEffect() {
        return tempsEffect;
    }

    public void setTempsEffect(int tempsEffect) {
        this.tempsEffect = tempsEffect;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEntrainements != null ? idEntrainements.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entrainements)) {
            return false;
        }
        Entrainements other = (Entrainements) object;
        if ((this.idEntrainements == null && other.idEntrainements != null) || (this.idEntrainements != null && !this.idEntrainements.equals(other.idEntrainements))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entrainements.Entrainements[ idEntrainements=" + idEntrainements + " ]";
    }
    
}
