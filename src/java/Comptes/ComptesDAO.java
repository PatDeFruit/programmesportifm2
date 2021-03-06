/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comptes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
    
    //Liste des comptes
    public List<Comptes> getAllComptesDiffLogin(String login) {
        Query query = em.createQuery("SELECT c FROM Comptes c left join c.idType t Where c.login != :login and c.idType.idType ='2'").setParameter("login", login);
        try{
        return query.getResultList();
        }catch(Exception e){
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur: Login déjà existant !",null));
            System.err.println(e.getMessage());
            return null;
        }
    }
     
        //compteur de la présence d'un login dans la BDD
    //trouver un niveau via l'Id
    public int getCountLogin(String login) {
        Query query = em.createQuery("SELECT COUNT(c) FROM Comptes c Where c.login = :login").setParameter("login", login);
        try{
            return ((Number) query.getSingleResult()).intValue();
        }catch(Exception e){
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur: Login déjà existant !",null));
            System.err.println(e.getMessage());
            return -1;
        }
    }
    
    //compteur du nombre de compte
    public int getCountComptes() {
        Query query = em.createQuery("SELECT COUNT(c) FROM Comptes c ");
        try{
            return ((Number) query.getSingleResult()).intValue();
        }catch(Exception e){
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur: Login déjà existant !",null));
            System.err.println(e.getMessage());
            return -1;
        }
    }
        
         //save Comptes
    public void saveComptes(Comptes newComptes){
        try{
            if(newComptes.getLogin() != null){
                em.persist(newComptes);
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
        }    
    }
    
    //Update
    //save Comptes
    public void updateComptes(Comptes newComptes){
        try{
            if(newComptes.getLogin()!= null){
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
            System.err.println(e.getMessage());
            return null;
        }
    }
        
    public Comptes getOneComptes(String login){
        Query query = em.createNamedQuery("Comptes.findByLogin").setParameter("login", login);
        try{
            return (Comptes) query.getSingleResult();
        }catch (NoResultException e){
            System.err.println("pas de Compte avec ce login");
            return null;
        }
    }
    
    public Comptes getOneEmailComptes(String email){
        Query query = em.createNamedQuery("Comptes.findByEmail").setParameter("email", email);
        try{
            return (Comptes) query.getSingleResult();
        }catch (NoResultException e){
            System.err.println("Pas de Compte avec cet email !");
            return null;
        }
    }
    
    //suppression
    public void suppCompte(Comptes newComptes){
        try{
            em.remove(em.merge(newComptes));
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    
}
