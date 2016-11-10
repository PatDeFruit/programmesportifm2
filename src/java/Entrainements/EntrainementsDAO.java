/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entrainements;

import Comptes.Comptes;
import Programmes.Programmes;
import java.util.Date;
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
public class EntrainementsDAO {

    //Entity Manager
    @PersistenceContext(unitName = "programmesportifm2PU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    //coder
    public List<Entrainements> getAllEntrainement() {
        Query query = em.createNamedQuery("Entrainements.findAll");
        return query.getResultList();
    }
    
  
    //Ajout
    public void saveEntrainement(Entrainements newEntrainement){
        try{
            if(newEntrainement.getIdEntrainements()!= null){
                em.merge(newEntrainement);
            }
            else {
                em.persist(newEntrainement);
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    
    //Update
    public void updateEntrainement(Entrainements newEntrainement){
        try{
            em.merge(newEntrainement);
        } catch (Exception e){
            System.err.println(e.getMessage());
        }   
    }
    
    //suppression
    public void suppEntrainement(Entrainements newEntrainement){
        try{
            em.remove(em.merge(newEntrainement));
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    
    public List<Entrainements> getEntrainementsByDate(Date d){
         Query query = em.createNamedQuery("Entrainements.findByDateEntrainement").setParameter("dateEntrainement", d);
        return query.getResultList();
    }
    
    
}
