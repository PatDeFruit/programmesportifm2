/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entrainements;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Asus
 */
@Named(value = "entrainementsController")
@Dependent
public class EntrainementsController implements Serializable{

    @EJB
    private EntrainementsDAO entrainementsDAO;

    
    
    /**
     * Creates a new instance of EntrainementsController
     */
    public EntrainementsController() {
    }
    
}
