/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comptes;

import Amitie.Amitie;
import Defis.Defis;
import Entrainements.Entrainements;
import Niveaux.Niveaux;
import Types_comptes.TypesComptes;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import sun.security.provider.MD5;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "comptes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comptes.findAll", query = "SELECT c FROM Comptes c"),
    @NamedQuery(name = "Comptes.findByLogin", query = "SELECT c FROM Comptes c WHERE c.login = :login"),
    @NamedQuery(name = "Comptes.findByPswd", query = "SELECT c FROM Comptes c WHERE c.pswd = :pswd"),
    @NamedQuery(name = "Comptes.findByNom", query = "SELECT c FROM Comptes c WHERE c.nom = :nom"),
    @NamedQuery(name = "Comptes.findByPrenom", query = "SELECT c FROM Comptes c WHERE c.prenom = :prenom"),
    @NamedQuery(name = "Comptes.findByEmail", query = "SELECT c FROM Comptes c WHERE c.email = :email"),
    @NamedQuery(name = "Comptes.findBySexe", query = "SELECT c FROM Comptes c WHERE c.sexe = :sexe"),
    @NamedQuery(name = "Comptes.findByAge", query = "SELECT c FROM Comptes c WHERE c.age = :age"),
    @NamedQuery(name = "Comptes.findByTaille", query = "SELECT c FROM Comptes c WHERE c.taille = :taille"),
    @NamedQuery(name = "Comptes.findByPoids", query = "SELECT c FROM Comptes c WHERE c.poids = :poids"),
    @NamedQuery(name = "Comptes.findByNbDefis", query = "SELECT c FROM Comptes c WHERE c.nbDefis = :nbDefis"),
    @NamedQuery(name = "Comptes.findByNbDefisEmportes", query = "SELECT c FROM Comptes c WHERE c.nbDefisEmportes = :nbDefisEmportes"),
    @NamedQuery(name = "Comptes.CountLogin", query = "SELECT COUNT(c) FROM Comptes c WHERE c.login = :login")
})
    

public class Comptes implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "login")
    private Collection<Entrainements> entrainementsCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "login2")
    private Collection<Defis> defisCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "login1")
    private Collection<Defis> defisCollection1;

    @JoinColumn(name = "idType", referencedColumnName = "idType")
    @ManyToOne(optional = false)
    private TypesComptes idType;
    @JoinColumn(name = "idNiveau", referencedColumnName = "idNiveau")
    @ManyToOne(optional = false)
    private Niveaux idNiveau;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "login2")
    private Collection<Amitie> amitieCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "login1")
    private Collection<Amitie> amitieCollection1;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "pswd")
    private String pswd;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "prenom")
    private String prenom;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sexe")
    private boolean sexe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "age")
    @Temporal(TemporalType.DATE)
    private Date age;
    @Column(name = "taille")
    private Integer taille;
    @Column(name = "poids")
    private Integer poids;
    @Column(name = "nbDefis")
    private Integer nbDefis;
    @Column(name = "nbDefisEmportes")
    private Integer nbDefisEmportes;

    public Comptes() {
    }

    public Comptes(String login) {
        this.login = login;
    }

    public Comptes(String login, String pswd, String nom, String prenom, String email, boolean sexe, Date age) {
        this.login = login;
        this.pswd = pswd;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.sexe = sexe;
        this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getSexe() {
        return sexe;
    }
    /////////////////////////////////////
    public String getSexeAffichage() {
        String sexAffichage;
        if (sexe == true){
            sexAffichage = "Femme";
        } else if (sexe == false) {
            sexAffichage = "Homme";
        }else {
            sexAffichage = "Non renseigné";
        }
        return sexAffichage;
    }

    public void setSexe(boolean sexe) {
        this.sexe = sexe;
    }

    public Date getAge() {
        return age;
    }

    public void setAge(Date age) {
        this.age = age;
    }

    public Integer getTaille() {
        return taille;
    }

    public void setTaille(Integer taille) {
        this.taille = taille;
    }

    public Integer getPoids() {
        return poids;
    }

    public void setPoids(Integer poids) {
        this.poids = poids;
    }

    public Integer getNbDefis() {
        return nbDefis;
    }

    public void setNbDefis(Integer nbDefis) {
        this.nbDefis = nbDefis;
    }

    public Integer getNbDefisEmportes() {
        return nbDefisEmportes;
    }

    public void setNbDefisEmportes(Integer nbDefisEmportes) {
        this.nbDefisEmportes = nbDefisEmportes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (login != null ? login.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comptes)) {
            return false;
        }
        Comptes other = (Comptes) object;
        if ((this.login == null && other.login != null) || (this.login != null && !this.login.equals(other.login))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Comptes.Comptes[ login=" + login + " ]";
    }

    public TypesComptes getIdType() {
        return idType;
    }

    public void setIdType(TypesComptes idType) {
        this.idType = idType;
    }

    public Niveaux getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(Niveaux idNiveau) {
        this.idNiveau = idNiveau;
    }

    @XmlTransient
    public Collection<Amitie> getAmitieCollection() {
        return amitieCollection;
    }

    public void setAmitieCollection(Collection<Amitie> amitieCollection) {
        this.amitieCollection = amitieCollection;
    }

    @XmlTransient
    public Collection<Amitie> getAmitieCollection1() {
        return amitieCollection1;
    }

    public void setAmitieCollection1(Collection<Amitie> amitieCollection1) {
        this.amitieCollection1 = amitieCollection1;
    }

    @XmlTransient
    public Collection<Defis> getDefisCollection() {
        return defisCollection;
    }

    public void setDefisCollection(Collection<Defis> defisCollection) {
        this.defisCollection = defisCollection;
    }

    @XmlTransient
    public Collection<Defis> getDefisCollection1() {
        return defisCollection1;
    }

    public void setDefisCollection1(Collection<Defis> defisCollection1) {
        this.defisCollection1 = defisCollection1;
    }

    @XmlTransient
    public Collection<Entrainements> getEntrainementsCollection() {
        return entrainementsCollection;
    }

    public void setEntrainementsCollection(Collection<Entrainements> entrainementsCollection) {
        this.entrainementsCollection = entrainementsCollection;
    }
    
}
