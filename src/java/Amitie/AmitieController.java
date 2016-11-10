/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Amitie;

import Comptes.Comptes;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import Comptes.ComptesDAO;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
@Named(value = "amitieController")
@ViewScoped
@ManagedBean
public class AmitieController implements Serializable{

    //insert code -> call entreprise bean -> AmitieDAO
    @EJB
    private AmitieDAO amitieDAO;
    
    @EJB
    private ComptesDAO comptesDAO;
    
    private boolean boolAmitie;
    
    private boolean boolAmitie2;
    
    private Amitie newAmitie;

    
    //getter et setter newAmitie
    public Amitie getNewAmitie() {
        return newAmitie;
    }

    public void setNewAmitie(Amitie newAmitie) {
        this.newAmitie = newAmitie;
    }

    public boolean isBoolAmitie() {
        return boolAmitie;
    }

    public void setBoolAmitie(boolean boolAmitie) {
        this.boolAmitie = boolAmitie;
    }

    public boolean isBoolAmitie2() {
        return boolAmitie2;
    }

    public void setBoolAmitie2(boolean boolAmitie2) {
        this.boolAmitie2 = boolAmitie2;
    }

    
    
    
    
    //getter du compte
    public List<Amitie> getAmitie(){
        return amitieDAO.getAllAmitie();
    }
    
     
    /**
     * Creates a new instance of AmitieController
     */
    public AmitieController() {
       newAmitie = new Amitie();
    }
    
    /**
     * get le nombre d'amities existantes
     * @return integer
     */
    //getter du compte
    public int getCountAmities(){
        return amitieDAO.getCountAmities();
    }
    

    
    public List<Comptes> getMyFriendsWithLogin1(String login){
        return amitieDAO.getMyFriendsWithLogin1(login);
    }
    
    public List<Comptes> getMyFriendsWithLogin2(String login){
        return amitieDAO.getMyFriendsWithLogin2(login);
    }
    
    public boolean compareAmitie1(String login){
        List<Comptes> listAmitie1 = amitieDAO.getMyFriendsWithLogin1(login);
        //List<Comptes> listAmitie2 = amitieDAO.getMyFriendsWithLogin2(login);
         
        List<Comptes> listeCompte = comptesDAO.getAllComptes();

        //je veux comparer listCompte total avec ListAmitie1 
            //si le compte est présent dans ListAmitie1 je veux retourner true ou 1
            // sinon retourner false ou 0
        ArrayList<Integer> al4= new ArrayList<Integer>();
          for (Comptes temp : listeCompte )
              boolAmitie =  al4.add(listAmitie1.contains(temp) ? 1 : 0);
          
          System.out.println(boolAmitie);

        //au final veux pouvoir affficher mes bouton dans le xhtml en fonction des 0 et 1
        
        return boolAmitie;
    }
    
    public boolean compareAmitie2(String login){
        List<Comptes> listAmitie2 = amitieDAO.getMyFriendsWithLogin2(login);
        //List<Comptes> listAmitie2 = amitieDAO.getMyFriendsWithLogin2(login);
         
        List<Comptes> listeCompte = comptesDAO.getAllComptes();

        //je veux comparer listCompte total avec ListAmitie1 
            //si le compte est présent dans ListAmitie1 je veux retourner true ou 1
            // sinon retourner false ou 0
        ArrayList<Integer> al5= new ArrayList<Integer>();
          for (Comptes temp : listeCompte )
              boolAmitie2 =  al5.add(listAmitie2.contains(temp) ? 1 : 0);
          
          System.out.println(boolAmitie);

        //au final veux pouvoir affficher mes bouton dans le xhtml en fonction des 0 et 1
        
        return boolAmitie2;
    }
}
