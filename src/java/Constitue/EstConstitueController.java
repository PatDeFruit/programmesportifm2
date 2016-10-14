/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Constitue;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Asus
 */
@Named(value = "constitueController")
@ViewScoped
public class EstConstitueController implements Serializable{

    @EJB
    private EstConstitueDAO estConstitueDAO;

    //getter du compte
    public List<EstConstitue> getEstConstitue(){
        return estConstitueDAO.getAllConstitue();
    }
    
    /**
     * Creates a new instance of ConstitueController
     */
    public EstConstitueController() {
    }
    
}
