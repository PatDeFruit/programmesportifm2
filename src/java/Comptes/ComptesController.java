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
import javax.faces.view.ViewScoped;
import Types_comptes.TypesComptesDAO;

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
        
    @EJB
    private NiveauxDAO niveauxDAO;
    
    @EJB
    private TypesComptesDAO typeDAO;
    
    private Comptes newComptes;
    private Niveaux niveaux;
    private TypesComptes typeComptes;
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
        int resultat = comptesDAO.getCountLogin(newComptes.getLogin());
        //System.out.println("**************NON ****"+newComptes.getLogin()+"*********************");
        //System.out.println("**********RES********"+resultat+"*********************");
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
            return "index";
        }
    }
}
