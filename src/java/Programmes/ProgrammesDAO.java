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
    
     public Programmes getByNameProgrammes(String prog) {
        Query query = em.createNamedQuery("Programmes.findByNomProgramme").setParameter("nomProgramme", prog);
        return (Programmes) query.getSingleResult();
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
        Query query = em.createQuery("SELECT DISTINCT p.nomProgramme FROM Programmes p, Entrainements e, Comptes c where c.login = :login").setParameter("login", login);
        try{
            return query.getResultList();
        } catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
    
        public Programmes getDefaultSelectedProgramme(String login){
        Query query = em.createQuery("SELECT DISTINCT p FROM Programmes p, Entrainements e, Comptes c where c.login = :login").setParameter("login", login);
        try{
            return (Programmes) query.getSingleResult();
        } catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
    
        //Ajout
    public void saveProgramme (Programmes newProg){
        try{
            if(newProg.getIdProgramme()!= null){
                em.merge(newProg);
            }
            else {
                em.persist(newProg);
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    
    //Update
    public void updateProgrammes(Programmes newProg){
        try{
            em.merge(newProg);
        } catch (Exception e){
            System.err.println(e.getMessage());
        }   
    }
    
    //suppression
    public void suppProgramme(Programmes newProg){
        try{
            em.remove(em.merge(newProg));
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    
     public Programmes updateSelectedProgramme(String prog){
        Query query = em.createQuery("SELECT DISTINCT p FROM Programmes p WHERE p.nomProgramme = :prog").setParameter("prog", prog);
        try{
            return (Programmes) query.getSingleResult();
        } catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
    
        public Programmes getTheIdProgramme(String prog){
        Query query = em.createQuery("SELECT p FROM Programmes p left join p.entrainementsCollection t WHERE t.idProgramme.nomProgramme = :prog").setParameter("prog", prog);
        try{
                return (Programmes) query.getResultList();
            } catch(Exception e){
                System.err.println(e.getMessage());
                return null;
            }
    }
     
}
