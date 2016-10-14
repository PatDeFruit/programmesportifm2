/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Programmes;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Asus
 */
@Named(value = "programmesController")
@ViewScoped
public class ProgrammesController implements Serializable{

    @EJB
    private ProgrammesDAO programmesDAO;

    
    
    /**
     * Creates a new instance of ProgrammesController
     */
    public ProgrammesController() {
    }
    
}
