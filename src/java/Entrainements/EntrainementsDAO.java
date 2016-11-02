/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entrainements;

import Comptes.Comptes;
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
    
    
   

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
