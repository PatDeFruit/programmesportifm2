/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comptes;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Asus
 */
@Named(value = "comptesController")
@ViewScoped
public class ComptesController {

    @EJB
    private ComptesDAO comptesDAO;

    
    
    /**
     * Creates a new instance of ComptesController
     */
    public ComptesController() {
    }
    
}
