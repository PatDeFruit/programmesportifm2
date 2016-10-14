/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stats;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Asus
 */
@Named(value = "statsExosController")
@ViewScoped
public class StatsExosController implements Serializable{

    @EJB
    private StatsExosDAO statsExosDAO;

    
    //getter du compte
    public List<StatsExos> getStatsExos(){
        return statsExosDAO.getAllStatsExos();
    }
    
    /**
     * Creates a new instance of StatsController
     */
    public StatsExosController() {
    }
    
}
