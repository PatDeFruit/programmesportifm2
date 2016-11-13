/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Amitie;

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
public class AmitieDAO {

    //entity Manager
    @PersistenceContext(unitName = "programmesportifm2PU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    //coder
    public List<Amitie> getAllAmitie() {
        Query query = em.createNamedQuery("Amitie.findAll");
        return query.getResultList();
    }
    
    //compteur du nombre d'amities
    public int getCountAmities() {
        Query query = em.createQuery("SELECT COUNT(a) FROM Amitie a ");
        try{
            return ((Number) query.getSingleResult()).intValue();
        }catch(Exception e){
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur: Login déjà existant !",null));
            System.err.println(e.getMessage());
            return -1;
        }
    }
    
    public List<Comptes> getMyFriendsWithLogin1(String login){
        Query query = em.createQuery("SELECT c FROM Comptes c left join c.amitieCollection a left join a.login1 log WHERE log.login = :login").setParameter("login", login);        
        try{
                return query.getResultList();
        } catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
    
    public List<Comptes> getPeopleNotMyFriends(String login){
        Query query = em.createQuery("SELECT c FROM Comptes c left join c.amitieCollection a left join a.login1 log left join a.login2 log2 WHERE log.login != :login AND log.login != c.login AND log2.login != c.login AND log2.login != :login AND c.login != :login").setParameter("login", login);        
        try{
                return query.getResultList();
        } catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
    
    public List<Comptes> getMyFriendsWithLogin2(String login){
        Query query = em.createQuery("SELECT c FROM Comptes c left join c.amitieCollection1 a left join a.login2 log2 WHERE log2.login = :login").setParameter("login", login);        
        try{
                return query.getResultList();
        } catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
    
    
    public Amitie getAmitieByLogs(String login1, String login2){
        Query query = em.createQuery("SELECT a FROM Amitie a left join a.login1 c left join a.login2 c2 WHERE c.login = :login1 AND c2.login = :login2").setParameter("login2", login2).setParameter("login1", login1);
        Query query2 = em.createQuery("SELECT a FROM Amitie a left join a.login1 c left join a.login2 c2 WHERE c.login = :login2 AND c2.login = :login1").setParameter("login2", login2).setParameter("login1", login1);
         try{
             if(query.getSingleResult() != null){
                return (Amitie) query.getSingleResult(); 
             } else {
                 return (Amitie) query2.getSingleResult();
             }
        } catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
         }
    
    
     public void suppAmitie(Amitie a){
        //suppression
        try{
            em.remove(em.merge(a));
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        }

     
       //Ajout
    public void saveAmitie(Amitie newAmitie){
        try{
            if(newAmitie.getIdAmitie()!= null){
                em.merge(newAmitie);
            }
            else {
                em.persist(newAmitie);
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
     
     
}

   