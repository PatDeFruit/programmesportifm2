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
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
