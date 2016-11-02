/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Programmes;

import Comptes.ComptesDAO;
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
@Named(value = "programmesController")
@ViewScoped
@ManagedBean
public class ProgrammesController implements Serializable{

    @EJB
    private ProgrammesDAO programmesDAO;
    
    @EJB
    private ComptesDAO compteDAO;
    
    private Programmes newProgramme;

    //getter du compte
    public List<Programmes> getProgrammes(){
        return programmesDAO.getAllProgrammes();
    }
    
    //Getter et Setter

    public Programmes getNewProgramme() {
        return newProgramme;
    }

    public void setNewProgramme(Programmes newProgramme) {
        this.newProgramme = newProgramme;
    }
    
    
    /**
     * Creates a new instance of ProgrammesController
     */
    public ProgrammesController() {
        newProgramme = new Programmes();
    }
    
    /**
     * get le nombre de programmes existant
     * @return integer
     */
    //getter du compte
    public int getCountProgrammes(){
        return programmesDAO.getCountProgrammes();
    }
    
    public List<Programmes> getMyProgrammes(String login){
        return programmesDAO.getMyProgrammes(login);
    }
    
}
