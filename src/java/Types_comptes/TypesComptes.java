/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Types_comptes;

import Comptes.Comptes;
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
@Table(name = "types_comptes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypesComptes.findAll", query = "SELECT t FROM TypesComptes t"),
    @NamedQuery(name = "TypesComptes.findByIdType", query = "SELECT t FROM TypesComptes t WHERE t.idType = :idType"),
    @NamedQuery(name = "TypesComptes.findByNomType", query = "SELECT t FROM TypesComptes t WHERE t.nomType = :nomType")})
public class TypesComptes implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idType")
    private Collection<Comptes> comptesCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idType")
    private Integer idType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nomType")
    private String nomType;

    public TypesComptes() {
    }

    public TypesComptes(Integer idType) {
        this.idType = idType;
    }

    public TypesComptes(Integer idType, String nomType) {
        this.idType = idType;
        this.nomType = nomType;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idType != null ? idType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypesComptes)) {
            return false;
        }
        TypesComptes other = (TypesComptes) object;
        if ((this.idType == null && other.idType != null) || (this.idType != null && !this.idType.equals(other.idType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Types_comptes.TypesComptes[ idType=" + idType + " ]";
    }

    @XmlTransient
    public Collection<Comptes> getComptesCollection() {
        return comptesCollection;
    }

    public void setComptesCollection(Collection<Comptes> comptesCollection) {
        this.comptesCollection = comptesCollection;
    }
    
}
