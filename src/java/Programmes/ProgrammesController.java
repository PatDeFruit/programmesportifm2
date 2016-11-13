/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Programmes;

import Comptes.Comptes;
import Comptes.ComptesController;
import Comptes.ComptesDAO;
import Entrainements.Entrainements;
import Entrainements.EntrainementsDAO;
import Exercices.ExercicesDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.Query;
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
    
    @EJB
    private EntrainementsDAO entrainementDAO;
    
    @EJB
    private ExercicesDAO exerciceDAO;
    
    
    
    private Programmes newProgramme;
    private Programmes selectedProgramme;
    private Entrainements entrainement;
    
    private List<Programmes> listeProgramme;
    private List<Programmes> temp;
    
    
    String maVariable;
    private List<SelectItem> myListProgrammes;

    
    /**
     * Creates a new instance of ProgrammesController
     */
    public ProgrammesController() {
        newProgramme = new Programmes();
    }
    
    @PostConstruct
    public void postConstruct(){
         listeProgramme = (List<Programmes>) programmesDAO.getAllProgrammes();
         entrainement = new Entrainements();
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


    public String getMaVariable() {
        return maVariable;
    }

    public void setMaVariable(String maVariable) {
        this.maVariable = maVariable;
    }

    public List<SelectItem> getMyListProgrammes() {
        return myListProgrammes;
    }

    public void setMyListProgrammes(List<SelectItem> myListProgrammes) {
        this.myListProgrammes = myListProgrammes;
    }

    public Entrainements getEntrainement() {
        return entrainement;
    }

    public void setEntrainement(Entrainements entrainement) {
        this.entrainement = entrainement;
    }
    
       
    
    
    /**
     * get le nombre de programmes existant
     * @return integer
     */
    //getter du compte
    public int getCountProgrammes(){
        return programmesDAO.getCountProgrammes();
    }
    
        public Programmes getByNameProgrammes(String prog) {
        return programmesDAO.getByNameProgrammes(prog);
    }
    
    public List<Programmes> getMyProgrammes(String login){
        return programmesDAO.getMyProgrammes(login);
    }
    
    public ProgrammesDAO getProgrammesDAO() {
        return programmesDAO;
    }

    public void setProgrammesDAO(ProgrammesDAO programmesDAO) {
        this.programmesDAO = programmesDAO;
    }

    public ComptesDAO getCompteDAO() {
        return compteDAO;
    }

    public void setCompteDAO(ComptesDAO compteDAO) {
        this.compteDAO = compteDAO;
    }

    public Programmes getSelectedProgramme() {
        return selectedProgramme;
    }

    public void setSelectedProgramme(Programmes selectedProgramme) {
        this.selectedProgramme = selectedProgramme;
    }

    public List<Programmes> getListeProgramme() {
        return listeProgramme;
    }

    public void setListeProgramme(List<Programmes> listeProgramme) {
        this.listeProgramme = listeProgramme;
    }
    
    
    
    /**
     *Ajout d'un nouveau programme 
     */
    public void saveProgramme() {
        programmesDAO.saveProgramme(newProgramme);
        FacesMessage msg = new FacesMessage("Successful", "Ajout de : " + newProgramme.getNomProgramme() +" réalisé");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }
    
        public void saveProgrammeAndExo(Comptes c) {
        programmesDAO.saveProgramme(newProgramme);
        
        entrainement.setIdExercice(exerciceDAO.getExoByName(maVariable));
        entrainement.setIdProgramme(newProgramme);
        entrainement.setLogin(c);
        entrainement.setDateEntrainement(new Date());
        entrainementDAO.saveEntrainement(entrainement);
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
    
    public void  getDefaultProgramme(Comptes c){
        selectedProgramme = programmesDAO.getDefaultSelectedProgramme(c.getLogin());
    }
    
    
    public void updateSelectedProgramme(String prog){
        setSelectedProgramme(programmesDAO.updateSelectedProgramme(prog));
    }
    
    public Programmes getTheIdProgramme(Programmes prog){
       return programmesDAO.getTheIdProgramme(prog.getNomProgramme());
    }

    
    public Programmes getTheIdProgrammeString(String prog){
       return programmesDAO.getTheIdProgramme(prog);
    }    
    
    
    
}
