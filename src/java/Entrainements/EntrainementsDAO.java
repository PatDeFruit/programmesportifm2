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
    
    //coder
    public List<Entrainements> getEntrainementByLogin(String login) {
        Query query = em.createQuery("Select e from Entrainements e where e.login.login = :login order by e.idProgramme.nomProgramme").setParameter("login", login);
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
    
    public List<Entrainements> getEntrainementsByDateAndByProgrammes(Date d, int prog){
        Query query = em.createQuery("SELECT DISTINCT e FROM Entrainements e left join e.idProgramme p WHERE p.idProgramme = :prog AND e.dateEntrainement = :d").setParameter("prog", prog).setParameter("d", d);
        try{
                return query.getResultList();
            } catch(Exception e){
                System.err.println(e.getMessage());
                return null;
            }
    }
    
        public List<Entrainements> getEntrainementsByProgrammes(int prog){
        Query query = em.createQuery("SELECT DISTINCT e FROM Entrainements e left join e.idProgramme p WHERE p.idProgramme = :prog").setParameter("prog", prog);
        try{
                return query.getResultList();
            } catch(Exception e){
                System.err.println(e.getMessage());
                return null;
            }
    }
        
        public List<Entrainements> getEntrainementsByProgrammesAndExercices(int prog, int exo){
        Query query = em.createQuery("SELECT DISTINCT e FROM Entrainements e left join e.idProgramme p left join e.idExercice ex WHERE p.idProgramme = :prog AND ex.idExercice = :exo").setParameter("prog", prog).setParameter("exo", exo);
        try{
                return query.getResultList();
            } catch(Exception e){
                System.err.println(e.getMessage());
                return null;
            }
    }
        
        public int getCountByProgrammesAndExercices(int prog, int exo){
        Query query = em.createQuery("SELECT COUNT(DISTINCT e) FROM Entrainements e left join e.idProgramme p left join e.idExercice ex WHERE p.idProgramme = :prog AND ex.idExercice = :exo").setParameter("prog", prog).setParameter("exo", exo);
        try{
                return ((Number) query.getSingleResult()).intValue();
            } catch(Exception e){
                System.err.println(e.getMessage());
                return -1;
            }
    }
    
    
}
