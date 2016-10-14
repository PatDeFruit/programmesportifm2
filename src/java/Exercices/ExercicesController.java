/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exercices;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Asus
 */
@Named(value = "exercicesController")
@ViewScoped
public class ExercicesController implements Serializable{

    @EJB
    private ExercicesDAO exercicesDAO;

    
    
    /**
     * Creates a new instance of ExercicesController
     */
    public ExercicesController() {
    }
    
}
