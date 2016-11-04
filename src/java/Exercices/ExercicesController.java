/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exercices;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.swing.SortOrder;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Asus
 */
@Named(value = "exercicesController")
@ViewScoped
public class ExercicesController implements Serializable{

    @EJB
    private ExercicesDAO exercicesDAO;
    
    private List<Exercices> listeExo;
    
    private Exercices saisie;
    

    //getter du compte   
    public ExercicesController(){
        super();
    }
    
    @PostConstruct
    public void postConstruct(){
         listeExo = (List<Exercices>) exercicesDAO.getAllExercices();
    }
    
    public List<Exercices> getExercices(){
        return listeExo;
    }


 
    // getter et setter   

    public Exercices getSaisie() {
        return saisie;
    }

    public void setSaisie(Exercices saisie) {
        this.saisie = saisie;
    }
    
    /**
     *Ajout d'un nouvel exercice 
     */
    public void saveExercice() {
        exercicesDAO.saveExercice(saisie);
        FacesMessage msg = new FacesMessage("Successful", "Ajout de : " + saisie.getNomExercice() +" réalisé");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }
    
    /**
     * modification des exercice via le Edit
     * @param event
     */   
    public void onRowEdit(RowEditEvent event) {
        Exercices ex = (Exercices) event.getObject();
                System.out.println("************"+ ex.getNomExercice()  +"************");
        exercicesDAO.updateExercice(ex);
        FacesMessage msg = new FacesMessage("Modification : ", ((Exercices) event.getObject()).getNomExercice());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Exercices) event.getObject()).getNomExercice());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
            
    /**
     * suppression des exercices
     * @param exo 
     */
    public void suppExo(Exercices exo){ 
        this.exercicesDAO.suppExo(exo);
        listeExo.remove(exo);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Exercice supprimé"));
        }
}
