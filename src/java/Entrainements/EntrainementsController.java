/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entrainements;

import Comptes.Comptes;
import Programmes.Programmes;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Asus
 */
@Named(value = "entrainementsController")
@Dependent
public class EntrainementsController implements Serializable{

    @EJB
    private EntrainementsDAO entrainementsDAO;
    
    private Entrainements result;
    private Programmes resultProgramme;
    

    //getter du compte
    public List<Entrainements> getEntrainements(){
        return entrainementsDAO.getAllEntrainement();
    }
    
    /**
     * Creates a new instance of EntrainementsController
     */
    public EntrainementsController() {
    }

}
