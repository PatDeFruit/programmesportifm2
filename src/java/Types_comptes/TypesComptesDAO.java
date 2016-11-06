/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Types_comptes;

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
public class TypesComptesDAO {

    //Entity Manager
    @PersistenceContext(unitName = "programmesportifm2PU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    //coder
    public List<TypesComptes> getAllTypesComptes() {
        Query query = em.createNamedQuery("TypesComptes.findAll");
        return query.getResultList();
    }
    
    //trouver un type via l'Id
    public TypesComptes getFindByOneTypesComptes(int id) {
        Query query = em.createNamedQuery("TypesComptes.findByIdType").setParameter("idType", id);
        return (TypesComptes) query.getSingleResult();
    }
    
    //Ajout
    public void saveTypesComptes(TypesComptes newTypesComptes){
        try{
            if(newTypesComptes.getIdType()!= null){
                em.merge(newTypesComptes);
            }
            else {
                em.persist(newTypesComptes);
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    
    //Update
    public void updateTypeCompte(TypesComptes newType){
        try{
            em.merge(newType);
        } catch (Exception e){
            System.err.println(e.getMessage());
        }   
    }
    
    //suppression
    public void suppTypesComptes(TypesComptes newType){
        try{
            em.remove(em.merge(newType));
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
