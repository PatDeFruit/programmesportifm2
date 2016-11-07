/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Defis;

import Exercices.Exercices;
import Exercices.ExercicesDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
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
    
    private List<Defis> listeDefis;

    
    /**
     * Creates a new instance of DefisController
     */
    public DefisController() {
        newDefis = new Defis();
    }
    
    @PostConstruct
    public void postConstruct(){
         listeDefis = (List<Defis>) defisDAO.getAllDefis();
    }
    
    //getter du compte
    public List<Defis> getDefis(){
        return listeDefis;
    }
    
    ///Getter et setter

    public Defis getNewDefis() {
        return newDefis;
    }

    public void setNewDefis(Defis newDefis) {
        this.newDefis = newDefis;
    }
    
    
    /**
     * get le nombre de defis existants
     * @return integer
     */
    public int getCountDefis(){
        return defisDAO.getCountDefis();
    }
    
    public List<Exercices> getMyDefis(String login){
        return defisDAO.getMyDefis(login);
     }
         
    /**
     * @return integer
     */
    public List<Defis> getAllDefisEnCours(){
        return defisDAO.getAllDefisEnCours();
    }
    
}
