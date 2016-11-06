/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Niveaux;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Asus
 */
@Named(value = "niveauxController")
@ViewScoped
public class NiveauxController implements Serializable{

    @EJB
    private NiveauxDAO niveauxDAO;
    private Niveaux newNiveaux;
    int id;
    private List<Niveaux> listeNiveau;
    
    //constructeur
     public NiveauxController() {
        newNiveaux = new Niveaux();
    }
    
    @PostConstruct
    public void postConstruct(){
         listeNiveau = (List<Niveaux>) niveauxDAO.getAllNiveaux();
    }
    
    
    //Getter et setter
     public List<Niveaux> getNiveaux(){
        return listeNiveau;
    }
    
    public Niveaux getNewNiveaux() {
        return newNiveaux;
    }

    public void setNewNiveaux(Niveaux newNiveaux) {
        this.newNiveaux = newNiveaux;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    /**
     * find one niveau
     */
    public void getFindByOneNiveaux(ComponentSystemEvent event){
        newNiveaux = niveauxDAO.getFindByOneNiveaux(id);
    }
    
    /**
     *Ajout d'un nouvel exercice 
     */
    public void saveNiveau() {
        niveauxDAO.saveNiveau(newNiveaux);
        FacesMessage msg = new FacesMessage("Successful", "Ajout de : " + newNiveaux.getNomNiveau() +" réalisé");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }
    
    /**
     * modification des exercice via le Edit
     * @param event
     */   
    public void onRowEdit(RowEditEvent event) {
        Niveaux niv = (Niveaux) event.getObject();
                System.out.println("************"+ niv.getNomNiveau()  +"************");
        niveauxDAO.updateNiveau(niv);
        FacesMessage msg = new FacesMessage("Modification : ", ((Niveaux) event.getObject()).getNomNiveau());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Niveaux) event.getObject()).getNomNiveau());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    /**
     * suppression des exercices
     * @param niv 
     */
    public void suppNiveau(Niveaux niv){ 
        this.niveauxDAO.suppNiveau(niv);
        listeNiveau.remove(niv);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Niveau supprimé"));
        }
}
