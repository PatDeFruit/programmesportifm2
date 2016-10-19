/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Niveaux;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Asus
 */
@Named(value = "niveauxController")
@ViewScoped
public class NiveauxController implements Serializable{

    @EJB
    private NiveauxDAO niveauxDAO;
    private Niveaux newNiveaux;
    int id;
    
    
    //getter
    public List<Niveaux> getNiveaux(){
        return niveauxDAO.getAllNiveaux();
    }

    public Niveaux getNewNiveaux() {
        return newNiveaux;
    }

    public void setNewNiveaux(Niveaux newNiveaux) {
        this.newNiveaux = newNiveaux;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    /**
     * Creates a new instance of NiveauxController
     */
    public NiveauxController() {
        newNiveaux = new Niveaux();
    }
    
    public void getFindByOneNiveaux(ComponentSystemEvent event){
        newNiveaux = niveauxDAO.getFindByOneNiveaux(id);
    }
    
    
}
