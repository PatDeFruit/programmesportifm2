/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Defis;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Asus
 */
@Named(value = "defisController")
@ViewScoped
public class DefisController {

    @EJB
    private DefisDAO defisDAO;

    
    
    /**
     * Creates a new instance of DefisController
     */
    public DefisController() {
    }
    
}
