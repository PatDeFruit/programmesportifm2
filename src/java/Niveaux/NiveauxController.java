/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Niveaux;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
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

    
    //getter du compte
    public List<Niveaux> getNiveaux(){
        return niveauxDAO.getAllNiveaux();
    }
    /**
     * Creates a new instance of NiveauxController
     */
    public NiveauxController() {
    }
    
}
