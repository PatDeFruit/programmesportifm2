/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Types_comptes;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Asus
 */
@Named(value = "typesComptesController")
@ViewScoped
public class TypesComptesController implements Serializable{

    @EJB
    private TypesComptesDAO typesComptesDAO;
    
    private TypesComptes saisie;

    private List<TypesComptes> listeTypesComptes;
    
    //constructeur
    public TypesComptesController() {
        saisie = new TypesComptes();
    }
    
    @PostConstruct
    public void postConstruct(){
         listeTypesComptes = (List<TypesComptes>) typesComptesDAO.getAllTypesComptes();
    }
    
    //getter et setter
    public List<TypesComptes> getTypesComptes(){
        return listeTypesComptes;
    }

    public TypesComptes getSaisie() {
        return saisie;
    }

    public void setSaisie(TypesComptes saisie) {
        this.saisie = saisie;
    }
    
        
    /**
     *Ajout d'un nouveau type de compte 
     */
    public void saveTypesComptes() {
        typesComptesDAO.saveTypesComptes(saisie);
        FacesMessage msg = new FacesMessage("Successful", "Ajout de : " + saisie.getNomType() +" réalisé");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }
    
    /**
     * modification des exercice via le Edit
     * @param event
     */   
    public void onRowEdit(RowEditEvent event) {
        TypesComptes typeC = (TypesComptes) event.getObject();
                System.out.println("************"+ typeC.getNomType()  +"************");
        typesComptesDAO.updateTypeCompte(typeC);
        FacesMessage msg = new FacesMessage("Modification : ", ((TypesComptes) event.getObject()).getNomType());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((TypesComptes) event.getObject()).getNomType());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    /**
     * suppression des exercices
     * @param niv 
     */
    public void suppTypesComptes(TypesComptes typeC){ 
        this.typesComptesDAO.suppTypesComptes(typeC);
        listeTypesComptes.remove(typeC);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Type de compte supprimé"));
        }
    
}
