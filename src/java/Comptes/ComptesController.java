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
import javax.inject.Named;
import org.primefaces.event.FlowEvent;
import Niveaux.NiveauxDAO;
import Types_comptes.TypesComptesDAO;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ComponentSystemEvent;

/**
 *
 * @author Asus
 */
@Named(value = "comptesController")
@SessionScoped
@ManagedBean
public class ComptesController implements Serializable{

    @EJB
    private ComptesDAO comptesDAO;
        
    @EJB
    private NiveauxDAO niveauxDAO;
    
    @EJB
    private TypesComptesDAO typeDAO;
    
    private Comptes newComptes;
    private Comptes compteConnecte;
    private Niveaux niveaux;
    private TypesComptes typeComptes;
    private Comptes result;
    private boolean skip;
    
    String login;
    
    

    //getter du compte
    public List<Comptes> getComptes(){
        return comptesDAO.getAllComptes();
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
    
    
    
    
    /**
     *  //constructeur
     */
    public ComptesController() {
        newComptes = new Comptes();
    }
    
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
            //modif du niveau
                //this.niveaux = niveauxDAO.getFindByOneNiveaux(1);
                //compteConnecte.setIdNiveau(this.niveaux);
            //modif du type
                //this.typeComptes = typeDAO.getFindByOneTypesComptes(2);
                //compteConnecte.setIdType(this.typeComptes);
            //update du compte
                comptesDAO.updateComptes(compteConnecte);  
                //FacesMessage msg = new FacesMessage("Successful", "Modification prise en compte" + compteConnecte.getLogin());
                //FacesContext.getCurrentInstance().addMessage(null, msg);
                //FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
                return "profil?login=#{comptesController.compteConnecte.login}";       
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
        return "index";
        }  
     
     //récupére les info d'un compte via le login
     public void lireCompte(ComponentSystemEvent event){
        compteConnecte = comptesDAO.getOneComptes(login);
    }
}
