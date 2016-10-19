/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comptes;

import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Asus
 */
@Stateless
public class ComptesDAO {

    //Entity Manager
    @PersistenceContext(unitName = "programmesportifm2PU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    //Liste des comptes
    public List<Comptes> getAllComptes() {
        Query query = em.createNamedQuery("Comptes.findAll");
        return query.getResultList();
    }
    

     //save Comptes
    public void saveComptes(Comptes newComptes){
        try{
            if(newComptes.getLogin() != null){
                em.merge(newComptes);
            }
            else {
                em.persist(newComptes);
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        
    }
    
        public Comptes connect(String login,String mdp){
        Query query = em.createQuery("SELECT c FROM Comptes c WHERE c.login = :login AND c.pswd = :pswd").setParameter("login", login).setParameter("pswd", mdp);
        try{
            return (Comptes) query.getSingleResult();
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur: Login/mot de passe invalide",null));
            System.err.println(e.getMessage());
            return null;
        }
    }
    
       
}
