/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comptes;

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
import javax.faces.view.ViewScoped;

/**
 *
 * @author Asus
 */
@Named(value = "comptesController")
@ViewScoped
@ManagedBean
public class ComptesController implements Serializable{

    @EJB
    private ComptesDAO comptesDAO;
    private Comptes newComptes;
    private  NiveauxDAO niveauxDAO;
    private Niveaux niveaux;
     private Comptes result;
    private boolean skip;

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
        FacesMessage msg = new FacesMessage("Successful", "Bienvenue :" + newComptes.getLogin());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        //System.out.println(niveaux.getFindByOneNiveaux(1));
        //niveaux = niveauxDAO.getFindByOneNiveaux(1);
        //newComptes.setIdNiveau(niveaux);
        //System.out.println("blabla"+ niveaux.getFindByOneNiveaux(1));
        comptesDAO.saveComptes(newComptes);
        return "inscription";
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
    

    
    public String connect(){
        result = comptesDAO.connect(newComptes.getLogin(),newComptes.getPswd());
        if(result != null){
            if(result.getIdType().getIdType()==1){
                return "home_user";
            }
            else{
                return "home_user";
            }
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Mot de passe ou Login incorrect!"));
            return "index";
        }
    }
}
