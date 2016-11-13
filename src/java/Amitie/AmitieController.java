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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

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
    private Amitie amitieToDelete;
    
    
    private List<SelectItem>myListFriends;
    private List<SelectItem>myListNoFriends;
    
    private String selectedFriend;
    private String selectedPeople;

    
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

    public List<SelectItem> getMyListFriends() {
        return myListFriends;
    }

    public void setMyListFriends(List<SelectItem> myListFriends) {
        this.myListFriends = myListFriends;
    }

    public String getSelectedFriend() {
        return selectedFriend;
    }

    public void setSelectedFriend(String selectedFriend) {
        this.selectedFriend = selectedFriend;
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

    public List<SelectItem> getMyListNoFriends() {
        return myListNoFriends;
    }

    public void setMyListNoFriends(List<SelectItem> myListNoFriends) {
        this.myListNoFriends = myListNoFriends;
    }

    public String getSelectedPeople() {
        return selectedPeople;
    }

    public void setSelectedPeople(String selectedPeople) {
        this.selectedPeople = selectedPeople;
    }
    
    public List<Comptes> getMyFriendsWithLogin(String login){
        return amitieDAO.getMyFriendsWithLogin(login);
    }
    
    public List<Comptes> getMyFriendsWithLogin1(String login){
        return amitieDAO.getMyFriendsWithLogin1(login);
    }
    
    public List<Comptes> getMyFriendsWithLogin2(String login){
        return amitieDAO.getMyFriendsWithLogin2(login);
    }
    
    public List<Comptes> getMyNoFriendsWithLogin(String login){
        return amitieDAO.getMyNoFriendsWithLogin(login);
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
    
    public List<SelectItem> oneMenuAllFriends(String login){
        myListFriends = new ArrayList<SelectItem>();
        myListFriends.add(new SelectItem("null", "Select"));
        List<Comptes> listFriendsAll = new ArrayList<Comptes>();
        List<Comptes> listFriends1 = amitieDAO.getMyFriendsWithLogin1(login);
        List<Comptes> listFriends2 = amitieDAO.getMyFriendsWithLogin2(login);
        for(int i =0; i < listFriends1.size(); i++){
            listFriendsAll.add(listFriends1.get(i));
        }
        for(int i =0; i < listFriends2.size(); i++){
            listFriendsAll.add(listFriends2.get(i));
        }    
        for(int i =0; i < listFriendsAll.size(); i++){
            myListFriends.add(new SelectItem(listFriendsAll.get(i).getLogin(),listFriendsAll.get(i).getLogin()));
        }        
        return myListFriends;
    }
    
    
        
    public List<SelectItem> oneMenuAllNoFriends(String login){
        myListNoFriends = new ArrayList<SelectItem>();
        myListNoFriends.add(new SelectItem("null", "Select"));
        List<Comptes> listNoFriendsAll = new ArrayList<Comptes>();
        listNoFriendsAll = amitieDAO.getMyNoFriendsWithLogin(login);   
        System.out.println("*************************"+listNoFriendsAll.size()+"********************");
        for(int i =0; i < listNoFriendsAll.size(); i++){
            
            myListNoFriends.add(new SelectItem(listNoFriendsAll.get(i).getLogin(),listNoFriendsAll.get(i).getLogin()));
        }        
        return myListNoFriends;
    }
    
     public void suppAmitie(String login){
         amitieToDelete = amitieDAO.getAmitieByLogs(login, selectedFriend);
         amitieDAO.suppAmitie(amitieToDelete);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Amitié supprimée"));
     }
    
     
          public void saveAmitie(String me) {
              System.out.println("***//////////*******************"+me+"************////////*************");
              System.out.println("**********///************"+selectedPeople+"*************************");
              
              Comptes amCompte = comptesDAO.getOneComptes(me);
                
            newAmitie.setLogin1(amCompte);
            
            Comptes amCompte2 = comptesDAO.getOneComptes(selectedPeople);
            newAmitie.setLogin2(amCompte2);
                
        amitieDAO.saveAmitie(newAmitie);
        
        FacesMessage msg = new FacesMessage("Successful", "Ajout de l'amitié");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }
}
