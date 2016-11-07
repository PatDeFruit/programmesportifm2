/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Amitie;

import Comptes.Comptes;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Asus
 */
@Named(value = "amitieController")
@ViewScoped
@ManagedBean
public class AmitieController implements Serializable{

    //insert code -> call entreprise bean -> AmitieDAO
    @EJB
    private AmitieDAO amitieDAO;
    
    private Amitie newAmitie;

    
    //getter et setter newAmitie

    public Amitie getNewAmitie() {
        return newAmitie;
    }

    public void setNewAmitie(Amitie newAmitie) {
        this.newAmitie = newAmitie;
    }
    
    
    
    //getter du compte
    public List<Amitie> getAmitie(){
        return amitieDAO.getAllAmitie();
    }
    
     
    /**
     * Creates a new instance of AmitieController
     */
    public AmitieController() {
       newAmitie = new Amitie();
    }
    
    /**
     * get le nombre d'amities existantes
     * @return integer
     */
    //getter du compte
    public int getCountAmities(){
        return amitieDAO.getCountAmities();
    }
    
    public List<Comptes> getMyFriendsWithLogin1(String login){
        return amitieDAO.getMyFriendsWithLogin1(login);
    }
    
    public List<Comptes> getMyFriendsWithLogin2(String login){
        return amitieDAO.getMyFriendsWithLogin2(login);
    }
    
}
