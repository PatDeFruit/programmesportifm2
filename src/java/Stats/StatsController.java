/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stats;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Asus
 */
@Named(value = "statsController")
@ViewScoped
public class StatsController {

    @EJB
    private StatsExosDAO statsExosDAO;

    
    
    
    /**
     * Creates a new instance of StatsController
     */
    public StatsController() {
    }
    
}
