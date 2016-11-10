/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comptes;

import Types_comptes.TypesComptes;
import Niveaux.Niveaux;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;
import Niveaux.NiveauxDAO;
import Programmes.Programmes;
import Programmes.ProgrammesDAO;
import Types_comptes.TypesComptesDAO;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Asus
 */

@SessionScoped
@ManagedBean
public class ComptesController implements Serializable{

    @EJB
    private ComptesDAO comptesDAO;
        
    @EJB
    private NiveauxDAO niveauxDAO;
    
    @EJB
    private TypesComptesDAO typeDAO;
    
    @EJB
    private ProgrammesDAO programmeDAO;
    
    private Comptes newComptes;
    private Comptes compteConnecte;
    private Niveaux niveaux;
    private TypesComptes typeComptes;
    private Comptes result;
    private boolean skip;
    private List<Comptes> listeCompte;
    private Programmes programmeSelected;
    
    private static int cptMdp;
    private static List<Comptes> listeCompteMDP = new ArrayList<Comptes>();
    
    String login;
    
    /**
     *  //constructeur
     */
    public ComptesController() {
        newComptes = new Comptes();
        
    }
    
    @PostConstruct
    public void postConstruct(){
         listeCompte = (List<Comptes>) comptesDAO.getAllComptes();
    }

    //getter du compte
    public static List<Comptes> getListeCompteMDP() {
        return listeCompteMDP;
    }
    
    public List<Comptes> getComptes(){
        return listeCompte;
    }
    
    // getter et setter du newComptes
    public Comptes getNewComptes() {
        return newComptes;
    }

    public void setNewComptes(Comptes newComptes) {
        this.newComptes = newComptes;
    }

    public Comptes getCompteConnecte() {
        return compteConnecte;
    }

    public void setCompteConnecte(Comptes compteConnecte) {
        this.compteConnecte = compteConnecte;
    }

    //getter et setter login
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public static int getCptMdp() {
        return cptMdp;
    }

    public static void setCptMdp(int cptMdp) {
        ComptesController.cptMdp = cptMdp;
    }




//////////////////////////////////////////////////////////////////////////////////////////////////    
    
       
    /**
     * //save 
     * @return
     */
    public String saveComptes() {
        int resultat = comptesDAO.getCountLogin(newComptes.getLogin());
        if (resultat == 0){   
            //ajout du niveau
                this.niveaux = niveauxDAO.getFindByOneNiveaux(1);
                newComptes.setIdNiveau(this.niveaux);
            //ajout du type
                this.typeComptes = typeDAO.getFindByOneTypesComptes(2);
                newComptes.setIdType(this.typeComptes);
            //création du nouveau compte
                comptesDAO.saveComptes(newComptes);  
                FacesMessage msg = new FacesMessage("Successful", "Bienvenu :" + newComptes.getLogin());
                FacesContext.getCurrentInstance().addMessage(null, msg);
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
                
                return "index";  
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur: Login déjà existant !",null));
            return "inscription";  
        }
        
    }
    
    /**
     * Update information du compte
     * @return string (nom de la page)
     */
    public String updateComptes() { 
        comptesDAO.updateComptes(compteConnecte);  
        return "profil?login=#{comptesController.compteConnecte.login}";       
    }
    
    /**
     * modification des exercice via le Edit
     * @param event
     */   
    public void onRowEdit(RowEditEvent event) {
        Comptes compte = (Comptes) event.getObject();
                System.out.println("************"+ compte.getLogin()  +"************");
        comptesDAO.updateComptes(compte);
        FacesMessage msg = new FacesMessage("Modification : ", ((Comptes) event.getObject()).getLogin());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Comptes) event.getObject()).getLogin());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    /**
     * get le nombre de compte existant
     * @return integer
     */
    //getter du compte
    public int getCountComptes(){
        return comptesDAO.getCountComptes();
    }
    
    
    
    /**
    * //skip pour passer les pages pour l'inscription 
    * @return
    */
    public boolean isSkip() {
        return skip;
    }
 
    public void setSkip(boolean skip) {
        this.skip = skip;
    }
    
    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset le bouton
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }

    public Programmes getProgrammeSelected() {
        return programmeSelected;
    }

    public void setProgrammeSelected(Programmes programmeSelected) {
        this.programmeSelected = programmeSelected;
    }
    
    
    
        
    /**
     * Fonction de connexion
     * @return string
     */
    public String connect(){
        result = comptesDAO.connect(newComptes.getLogin(),newComptes.getPswd());
        compteConnecte = result;
        if(result != null){
            if(result.getIdType().getIdType()==1){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Connexion réussie !", "Vous êtes connecté en tant que modérateur."));
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
                return "home_admin";
            }
            else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Connexion réussie !", "Vous êtes connecté"));
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
                return "home_user";
            }
           
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Mot de passe ou Login incorrect!"));
            return "index";
        }        
    }
    
     public String disconnect(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("comptesController");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Déconnexion !", "Vous êtes déconnecté"));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
         System.out.println("********************"+cptMdp+"****************");
        return "index";
        }  
     
     //récupére les info d'un compte via le login
     public void lireCompte(ComponentSystemEvent event){
        compteConnecte = comptesDAO.getOneComptes(login);
    }
     
    public Comptes getOneEmailComptes(String email){
        return comptesDAO.getOneEmailComptes(email);
    }
     
     /**
     * suppression des exercices
     * @param compte 
     */
    public void suppCompte(Comptes compte){ 
        this.comptesDAO.suppCompte(compte);
        listeCompte.remove(compte);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Compte supprimé"));
        }
    
    /**
     * notification modif compte
     * @param emailCompte
     * @param validate
     * @return int
     */
    public List<Comptes> notifcompte(String emailCompte, boolean validate){ 
        //this.comptesDAO.suppCompte(compte);
        //listeCompte.remove(compte);

            Comptes c = comptesDAO.getOneEmailComptes(emailCompte);
          
        if (validate == false){
         cptMdp++;   
         listeCompteMDP.add(c);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Information : ", "Demande de modification de mot de passe envoyée !"));
             FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
        else{
          cptMdp--;
          listeCompteMDP.remove(c);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Information : ", "Modification prise en compte !"));
             FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }    
        return listeCompteMDP;
    }
}
