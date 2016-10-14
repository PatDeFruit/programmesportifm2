/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Constitue;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Asus
 */
@Named(value = "constitueController")
@ViewScoped
public class ConstitueController implements Serializable{

    @EJB
    private ConstitueDAO constitueDAO;

    
    
    /**
     * Creates a new instance of ConstitueController
     */
    public ConstitueController() {
    }
    
}
