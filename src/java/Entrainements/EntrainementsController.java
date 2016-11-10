/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entrainements;

import Comptes.Comptes;
import Exercices.Exercices;
import Exercices.ExercicesController;
import Exercices.ExercicesDAO;
import Programmes.Programmes;
import Programmes.ProgrammesDAO;
import java.io.Serializable;
//import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.Query;
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
    private Entrainements newEntrainement;
    private Programmes resultProgramme;
    private Programmes myProgramme;
    private ProgrammesDAO programmeDAO;
    private ExercicesDAO exerciceDAO;
    private Exercices myExercice;
    private Comptes myCompte;
    
    private Entrainements saisie;
    private String maVariable;
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

    public String getMaVariable() {
        return maVariable;
    }

    public void setMaVariable(String maVariable) {
        this.maVariable = maVariable;
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
     * @param prog
     * @param c
     */
    public String saveEntrainement() {
        System.out.println("*********************************"+getMaVariable()+"*****************************");
        saisie.setIdExercice(exerciceDAO.getExoByName(maVariable));
        System.out.println("*********************************"+saisie.getIdExercice().getNomExercice()+"*****************************");
//        saisie.setIdProgramme(this.myProgramme);
        System.out.println("*********************************"+saisie.getIdProgramme().getNomProgramme()+"*****************************");
//        this.myCompte = c;
        System.out.println("*********************************"+saisie.getLogin().getLogin()+"*****************************");
        //saisie.setLogin(myCompte);
        //saisie.setDateEntrainement(Date.from(Instant.MIN));
        entrainementsDAO.saveEntrainement(saisie);
        FacesMessage msg = new FacesMessage("Successful", "Ajout du : " + saisie.getDateEntrainement() +" réalisé");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return("home_user");
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

    public List<Entrainements> getEntrainementsByDate(Date d){
        return entrainementsDAO.getEntrainementsByDate(d);
    }
}
