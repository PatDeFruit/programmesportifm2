/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stats;

import Constitue.EstConstitue;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "stats_exos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatsExos.findAll", query = "SELECT s FROM StatsExos s"),
    @NamedQuery(name = "StatsExos.findByIdStat", query = "SELECT s FROM StatsExos s WHERE s.idStat = :idStat"),
    @NamedQuery(name = "StatsExos.findByNbRepetitions", query = "SELECT s FROM StatsExos s WHERE s.nbRepetitions = :nbRepetitions"),
    @NamedQuery(name = "StatsExos.findByNbSeries", query = "SELECT s FROM StatsExos s WHERE s.nbSeries = :nbSeries"),
    @NamedQuery(name = "StatsExos.findByTemps", query = "SELECT s FROM StatsExos s WHERE s.temps = :temps")})
public class StatsExos implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idStat")
    private Collection<EstConstitue> estConstitueCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idStat")
    private Integer idStat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nbRepetitions")
    private int nbRepetitions;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nbSeries")
    private int nbSeries;
    @Basic(optional = false)
    @NotNull
    @Column(name = "temps")
    private int temps;

    public StatsExos() {
    }

    public StatsExos(Integer idStat) {
        this.idStat = idStat;
    }

    public StatsExos(Integer idStat, int nbRepetitions, int nbSeries, int temps) {
        this.idStat = idStat;
        this.nbRepetitions = nbRepetitions;
        this.nbSeries = nbSeries;
        this.temps = temps;
    }

    public Integer getIdStat() {
        return idStat;
    }

    public void setIdStat(Integer idStat) {
        this.idStat = idStat;
    }

    public int getNbRepetitions() {
        return nbRepetitions;
    }

    public void setNbRepetitions(int nbRepetitions) {
        this.nbRepetitions = nbRepetitions;
    }

    public int getNbSeries() {
        return nbSeries;
    }

    public void setNbSeries(int nbSeries) {
        this.nbSeries = nbSeries;
    }

    public int getTemps() {
        return temps;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStat != null ? idStat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatsExos)) {
            return false;
        }
        StatsExos other = (StatsExos) object;
        if ((this.idStat == null && other.idStat != null) || (this.idStat != null && !this.idStat.equals(other.idStat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Stats.StatsExos[ idStat=" + idStat + " ]";
    }

    @XmlTransient
    public Collection<EstConstitue> getEstConstitueCollection() {
        return estConstitueCollection;
    }

    public void setEstConstitueCollection(Collection<EstConstitue> estConstitueCollection) {
        this.estConstitueCollection = estConstitueCollection;
    }
    
}
