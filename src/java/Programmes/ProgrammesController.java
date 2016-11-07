/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Programmes;

import Comptes.ComptesDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;

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
    
    private List<Programmes> listeProgramme;

    
    /**
     * Creates a new instance of ProgrammesController
     */
    public ProgrammesController() {
        newProgramme = new Programmes();
    }
    
    @PostConstruct
    public void postConstruct(){
         listeProgramme = (List<Programmes>) programmesDAO.getAllProgrammes();
    }
    
    //getter
    public List<Programmes> getProgrammes(){
        return listeProgramme;
    }
    
    //Getter et Setter

    public Programmes getNewProgramme() {
        return newProgramme;
    }

    public void setNewProgramme(Programmes newProgramme) {
        this.newProgramme = newProgramme;
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
    
    /**
     *Ajout d'un nouvel exercice 
     */
    public void saveProgramme() {
        programmesDAO.saveProgramme(newProgramme);
        FacesMessage msg = new FacesMessage("Successful", "Ajout de : " + newProgramme.getNomProgramme() +" réalisé");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }
    
    /**
     * modification des exercice via le Edit
     * @param event
     */   
    public void onRowEdit(RowEditEvent event) {
        Programmes prog = (Programmes) event.getObject();
                System.out.println("************"+ prog.getNomProgramme()  +"************");
        programmesDAO.updateProgrammes(prog);
        FacesMessage msg = new FacesMessage("Modification : ", ((Programmes) event.getObject()).getNomProgramme());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Programmes) event.getObject()).getNomProgramme());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    /**
     * suppression des exercices
     * @param prog 
     */
    public void suppProgramme(Programmes prog){ 
        this.programmesDAO.suppProgramme(prog);
        listeProgramme.remove(prog);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Programme supprimé"));
        }
}
