/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Defis;

import Exercices.Exercices;
import Exercices.ExercicesDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Asus
 */

@ViewScoped
@ManagedBean
public class DefisController implements Serializable{

    @EJB
    private DefisDAO defisDAO;
    
    private Defis newDefis;
    
    private List<Defis> listeDefis;
    
    private int cptDefis;
    
    private List<Defis> listeDefisVal = new ArrayList<Defis>();

    
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

    public int getCptDefis() {
        return cptDefis;
    }

    public void setCptDefis(int cptDefis) {
        this.cptDefis = cptDefis;
    }

    public List<Defis> getListeDefisVal() {
        return listeDefisVal;
    }

    public void setListeDefisVal(List<Defis> listeDefisVal) {
        this.listeDefisVal = listeDefisVal;
    }
    
    
    /**
     * get le nombre de defis existants
     * @return integer
     */
    public int getCountDefis(){
        return defisDAO.getCountDefis();
    }
    
    /**
     * 
     * @param login
     * @return 
     */
    public List<Exercices> getMyDefis(String login){
    return defisDAO.getMyDefis(login);
     }
         
    /**
     * @return integer
     */
    public List<Defis> getAllDefisEnCours(){
        return defisDAO.getAllDefisEnCours();
    }
    
    
    
    
    /**
     * modification des exercice via le Edit
     * @param event
     */   
    public void onRowEdit(RowEditEvent event) {
        Defis d = (Defis) event.getObject();
        defisDAO.updateDefisEnCours(d);
        FacesMessage msg = new FacesMessage("Modification : ", ((Defis) event.getObject()).getLogin1().getLogin());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Defis) event.getObject()).getLogin1().getLogin());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
    
    
    
    
    
    
    
    
    
    /**
     * notification modif compte
     * @param idDefis
     * @return int
     */
    public List<Defis> notifValidationDefis(int idDefis, boolean validate){ 
        //this.comptesDAO.suppCompte(compte);
        //listeCompte.remove(compte);

            Defis d = defisDAO.getOneDefis(idDefis);
          
        if (validate == false){
         cptDefis++;   
         listeDefisVal.add(d);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Information : ", "Défis réalisé. Demande de validation envoyé au médiateur !"));
             FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
        else{
          cptDefis--;
          listeDefisVal.remove(d);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Information : ", "Défis Validé !"));
             FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }    
        return listeDefisVal;
    }
    
    
   
}
