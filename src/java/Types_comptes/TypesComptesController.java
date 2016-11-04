/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Types_comptes;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

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

    
    //getter du compte
    public List<TypesComptes> getTypesComptes(){
        return typesComptesDAO.getAllTypesComptes();
    }
    
    //getter et setter
    public TypesComptes getSaisie() {
        return saisie;
    }

    public void setSaisie(TypesComptes saisie) {
        this.saisie = saisie;
    }
    
    
    
    /**
     * Creates a new instance of TypesComptesController
     */
    public TypesComptesController() {
    }
    
}
