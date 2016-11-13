/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Defis;

import Comptes.ComptesDAO;
import Exercices.Exercices;
import Exercices.ExercicesDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @EJB
    private ExercicesDAO exerciceDAO;

    @EJB
    private ComptesDAO compteDAO;
    
    private Defis newDefis;
    private Defis saisie;
    private Defis selectedDefi;
    
    private List<Defis> listeDefis;
    
    private int cptDefis;
    private String selectedExo;
    private String selectedFriend;
    
    private List<Defis> listeDefisVal = new ArrayList<Defis>();
    @PersistenceContext(unitName = "programmesportifm2PU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

   
    
    
    /**
     * Creates a new instance of DefisController
     */
    public DefisController() {
        newDefis = new Defis();
        saisie = new Defis();
        selectedDefi = new Defis();
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
     * 
     * @param login
     * @return 
     */
    public List<Defis> getAllMyDefisEffectue(String login){
    return defisDAO.getAllMyDefisEffectue(login);
     }
    
    /**
     * 
     * @param login
     * @return 
     */
    public int countAllMyDefisEffectue(String login){
        return defisDAO.countAllMyDefisEffectue(login);
     }
    
        /**
     * 
     * @param login
     * @return 
     */
    public List<Defis> getAllMyDefisCours(String login){
    return defisDAO.getAllMyDefisCours(login);
     }

    public Defis getSelectedDefi() {
        return selectedDefi;
    }

    public void setSelectedDefi(Defis selectedDefi) {
        this.selectedDefi = selectedDefi;
    }

    
    
    
    /**
     * 
     * @param login
     * @return 
     */
    public int countAllMyDefisCours(String login){
        return defisDAO.countAllMyDefisCours(login);
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

    public Defis getSaisie() {
        return saisie;
    }

    public void setSaisie(Defis saisie) {
        this.saisie = saisie;
    }

    public String getSelectedExo() {
        return selectedExo;
    }

    public void setSelectedExo(String selectedExo) {
        this.selectedExo = selectedExo;
    }

    public String getSelectedFriend() {
        return selectedFriend;
    }

    public void setSelectedFriend(String selectedFriend) {
        this.selectedFriend = selectedFriend;
    }
    
    
     public void defiRealise(Defis d){
         d.setEffectue(true);
         this.defisDAO.defiRealise(d);
         cptDefis++;
         FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Défi réalisé!"));
     }
    
     public void defiAnnule(Defis d){
          this.defisDAO.defiAnnule(d);
        listeDefis.remove(d);
        cptDefis--;
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Défi supprimé"));
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
    
    
     public void saveDefi(String me) {
        saisie.setEffectue(false);
        saisie.setLogin1(compteDAO.getOneComptes(me));
        saisie.setLogin2(compteDAO.getOneComptes(selectedFriend));
        saisie.setIdExercice(exerciceDAO.getExoByName(selectedExo));
        
        defisDAO.saveDefis(saisie);
        FacesMessage msg = new FacesMessage("Successful", "Ajout du défi : " + saisie.getIdExercice().getNomExercice()+" pour "+ saisie.getLogin2().getLogin() +"réalisé");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }
   
}
