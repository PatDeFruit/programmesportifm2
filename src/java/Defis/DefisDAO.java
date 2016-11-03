/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Defis;

import Exercices.Exercices;
import Programmes.Programmes;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Asus
 */
@Stateless
public class DefisDAO {

    //Entity Manager
    @PersistenceContext(unitName = "programmesportifm2PU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    //coder
    public List<Defis> getAllDefis() {
        Query query = em.createNamedQuery("Defis.findAll");
        return query.getResultList();
    }
    
    //compteur du nombre de compte
    public int getCountDefis() {
        Query query = em.createQuery("SELECT COUNT(d) FROM Defis d");
        try{
            return ((Number) query.getSingleResult()).intValue();
        }catch(Exception e){
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur: Login déjà existant !",null));
            System.err.println(e.getMessage());
            return -1;
        }
    }
    

     public List<Exercices> getMyDefis(String login){
            Query query = em.createQuery("SELECT e FROM Defis d, Exercices e, Comptes c WHERE c.login = :login AND d.effectue='0' AND e.idExercice = d.idExercice").setParameter("login", login);
            try{
                return query.getResultList();
            } catch(Exception e){
                System.err.println(e.getMessage());
                return null;
            }
        }
}
