/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exercices;

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
    
    //Ajout
    public void saveExercice(Exercices newExercice){
        try{
            if(newExercice.getIdExercice()!= null){
                em.merge(newExercice);
            }
            else {
                em.persist(newExercice);
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    
    //Update
    public void updateExercice(Exercices newExercices){
        try{
            em.merge(newExercices);
        } catch (Exception e){
            System.err.println(e.getMessage());
        }   
    }
    
    //suppression
    public void suppExo(Exercices newExercices){
        try{
            em.remove(em.merge(newExercices));
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    
    public List<Exercices> getExoByProgrammes(String prog){
        Query query = em.createQuery("SELECT DISTINCT e FROM Exercices e left join e.entrainementsCollection t left join t.idProgramme p WHERE p.nomProgramme = :prog").setParameter("prog", prog);
        try{
                return query.getResultList();
            } catch(Exception e){
                System.err.println(e.getMessage());
                return null;
            }
    }
    

}
