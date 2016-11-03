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
    
    private List<Exercices> filtreExo;
    
    private Exercices saisie;
    

    //getter du compte
    public List<Exercices> getExercices(){
        return exercicesDAO.getAllExercices();
    }
    
    public List<Exercices> getFiltreNomExercices(){
        return exercicesDAO.getNomExercices();
    }
    
    public List<Exercices> getFiltreDifficulteExercices(){
        return exercicesDAO.getDifficulteExercice();
    }
 
    // getter et setter   

    public List<Exercices> getFiltreExo() {
        return filtreExo;
    }

    public void setFiltreExo(List<Exercices> filtreExo) {
        this.filtreExo = filtreExo;
    }

    public Exercices getSaisie() {
        return saisie;
    }

    public void setSaisie(Exercices saisie) {
        this.saisie = saisie;
    }
    
    
    
    /**
     * Creates a new instance of ExercicesController
     */
    public ExercicesController() {
    }
    
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Exo Edited", ((Exercices) event.getObject()).getNomExercice());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Exercices) event.getObject()).getNomExercice());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
           // exercicesDAO.updateExercice(saisie);
            
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
}
