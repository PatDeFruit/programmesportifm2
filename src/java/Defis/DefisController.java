/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Defis;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Asus
 */
@Named(value = "defisController")
@ViewScoped
@ManagedBean
public class DefisController implements Serializable{

    @EJB
    private DefisDAO defisDAO;
    
    private Defis newDefis;

    //getter du compte
    public List<Defis> getDefis(){
        return defisDAO.getAllDefis();
    }
    
    ///Getter et setter

    public Defis getNewDefis() {
        return newDefis;
    }

    public void setNewDefis(Defis newDefis) {
        this.newDefis = newDefis;
    }
    
    
    
    /**
     * Creates a new instance of DefisController
     */
    public DefisController() {
        newDefis = new Defis();
    }
    
    /**
     * get le nombre de defis existants
     * @return integer
     */
    //getter du compte
    public int getCountDefis(){
        return defisDAO.getCountDefis();
    }
    
}
