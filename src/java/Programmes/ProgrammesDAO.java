/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Programmes;

import Comptes.Comptes;
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
public class ProgrammesDAO {

    //Entity Manager
    @PersistenceContext(unitName = "programmesportifm2PU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    //coder
    public List<Programmes> getAllProgrammes() {
        Query query = em.createNamedQuery("Programmes.findAll");
        return query.getResultList();
    }

    //compteur du nombre de compte
    public int getCountProgrammes() {
        Query query = em.createQuery("SELECT COUNT(p) FROM Programmes p");
        try{
            return ((Number) query.getSingleResult()).intValue();
        }catch(Exception e){
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur: Login déjà existant !",null));
            System.err.println(e.getMessage());
            return -1;
        }
    }
    
    public List<Programmes> getMyProgrammes(String login){
        Query query = em.createNativeQuery("SELECT p FROM Programmes p INNER JOIN Entrainements e USING(idProgramme) INNER JOIN Comptes c USING(login) WHERE c.login = :login").setParameter("login", login);
        try{
            return query.getResultList();
        } catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
