/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entrainements;

import Comptes.Comptes;
import Programmes.Programmes;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Asus
 */
@Named(value = "entrainementsController")
@Dependent
public class EntrainementsController implements Serializable{

    @EJB
    private EntrainementsDAO entrainementsDAO;
    
    private Entrainements result;
    private Programmes resultProgramme;
    
    private Entrainements saisie;
    
    private List<Entrainements> listeEntrainements;
    

    /**
     * Creates a new instance of EntrainementsController
     */
    public EntrainementsController() {
        saisie = new Entrainements();
    }
    
    @PostConstruct
    public void postConstruct(){
         listeEntrainements = (List<Entrainements>) entrainementsDAO.getAllEntrainement();
    }
    
       
    //getter du compte
    public List<Entrainements> getEntrainements(){
        return listeEntrainements;
    }

    public Entrainements getSaisie() {
        return saisie;
    }

    public void setSaisie(Entrainements saisie) {
        this.saisie = saisie;
    }
    
    /**
     *Ajout d'un nouvel exercice 
     */
    public void saveEntrainement() {
        entrainementsDAO.saveEntrainement(saisie);
        FacesMessage msg = new FacesMessage("Successful", "Ajout de : " + saisie.getDateEntrainement() +" réalisé");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }
    
    /**
     * modification des exercice via le Edit
     * @param event
     */   
    public void onRowEdit(RowEditEvent event) {
        Entrainements ent = (Entrainements) event.getObject();
                System.out.println("************"+ ent.getDateEntrainement()  +"************");
        entrainementsDAO.updateEntrainement(ent);
        FacesMessage msg = new FacesMessage("Modification effectuée ! ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
     public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
     /**
     * suppression
     * @param ent 
     */
    public void suppEntrainement(Entrainements ent){ 
        this.entrainementsDAO.suppEntrainement(ent);
        listeEntrainements.remove(ent);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Niveau supprimé"));
        }
    
    
}
