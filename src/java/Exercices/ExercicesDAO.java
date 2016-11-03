/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exercices;

import Defis.Defis;
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
public class ExercicesDAO {
    
    //Entity Manager
    @PersistenceContext(unitName = "programmesportifm2PU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    //coder
    public List<Exercices> getAllExercices() {
        Query query = em.createNamedQuery("Exercices.findAll");
        return query.getResultList();
    }
    

    //coder
    public List<Exercices> getNomExercices() {
        Query query = em.createQuery("SELECT e.nomExercice FROM Exercices e");
        return query.getResultList();
    }
    
    //coder
    public List<Exercices> getDifficulteExercice() {
        Query query = em.createQuery("SELECT e.difficulte FROM Exercices e");
        return query.getResultList();
    }
    
    //Update
    public void updateExercice(Exercices newExercices){
        try{
            em.merge(newExercices);
        } catch (Exception e){
            System.err.println(e.getMessage());
        }   
    }

}
