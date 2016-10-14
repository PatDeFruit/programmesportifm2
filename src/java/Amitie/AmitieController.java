/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Amitie;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Asus
 */
@Named(value = "amitieController")
@ViewScoped
public class AmitieController implements Serializable{

    //insert code -> call entreprise bean -> AmitieDAO
    @EJB
    private AmitieDAO amitieDAO;

    //getter du compte
    public List<Amitie> getAmitie(){
        return amitieDAO.getAllAmitie();
    }
    
     
    /**
     * Creates a new instance of AmitieController
     */
    public AmitieController() {
    }
    
}
