/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Niveaux;

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
public class NiveauxDAO {

    //Entity Manager
    @PersistenceContext(unitName = "programmesportifm2PU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

//coder
    public List<Niveaux> getAllNiveaux() {
        Query query = em.createNamedQuery("Niveaux.findAll");
        return query.getResultList();
    }
    
//trouver un niveau via l'Id
    public Niveaux getFindByOneNiveaux(int id) {
        Query query = em.createNamedQuery("Niveaux.findByIdNiveau").setParameter("idNiveau", id);
        return (Niveaux) query.getSingleResult();
    }
    
    //Ajout
    public void saveNiveau(Niveaux newNiveau){
        try{
            if(newNiveau.getIdNiveau()!= null){
                em.merge(newNiveau);
            }
            else {
                em.persist(newNiveau);
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    
    //Update
    public void updateNiveau(Niveaux newNiveaux){
        try{
            em.merge(newNiveaux);
        } catch (Exception e){
            System.err.println(e.getMessage());
        }   
    }
    
    //suppression
    public void suppNiveau(Niveaux newNiveaux){
        try{
            em.remove(em.merge(newNiveaux));
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
