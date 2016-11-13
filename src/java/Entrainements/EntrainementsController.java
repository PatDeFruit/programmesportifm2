/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entrainements;

import Comptes.Comptes;
import Comptes.ComptesDAO;
import Exercices.Exercices;
import Exercices.ExercicesController;
import Exercices.ExercicesDAO;
import Programmes.Programmes;
import Programmes.ProgrammesDAO;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.Query;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author Asus
 */
@Named(value = "entrainementsController")
@ViewScoped
public class EntrainementsController implements Serializable{

    @EJB
    private EntrainementsDAO entrainementsDAO;
    
    private Entrainements result;
    private Entrainements newEntrainement;
    private Programmes resultProgramme;
    private Programmes myProgramme;
    
    @EJB
    private ProgrammesDAO programmeDAO;
    @EJB
    private ExercicesDAO exerciceDAO;
    @EJB
    private ComptesDAO compteDAO;
    
    private Exercices myExercice;
    
    private Comptes myCompte;
   
    private Calendar calendar;
    private Calendar calendar2;
  
    private Date today;
    private Date yesterday;
    
   
    
    private Entrainements saisie;
    private String maVariable;
    private List<Entrainements> listeEntrainements;
    private List<Entrainements> listeEntrainementsByProgrammes;
    private List<Entrainements> listeEntrainementsByExos;
    private List<Exercices> listeExoByProgrammes;
    private int countEntrainementsByExos;
    private LineChartModel dateModel;
    
    

    /**
     * Creates a new instance of EntrainementsController
     */
    public EntrainementsController() {
        saisie = new Entrainements();
        calendar = new GregorianCalendar();
        calendar.add(Calendar.DATE, 0);
        today = calendar.getTime();
        calendar2 = new GregorianCalendar();
        calendar2.add(Calendar.DATE, -1);
        yesterday = calendar2.getTime();
    }
    
    @PostConstruct
    public void postConstruct(){
         listeEntrainements = (List<Entrainements>) entrainementsDAO.getAllEntrainement();
    }

    public String getMaVariable() {
        return maVariable;
    }

    public void setMaVariable(String maVariable) {
        this.maVariable = maVariable;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public Date getYesterday() {
        return yesterday;
    }

    public void setYesterday(Date yesterday) {
        this.yesterday = yesterday;
    }
    
    
       
    //getter du compte
    public List<Entrainements> getEntrainements(){
        return listeEntrainements;
    }

    public Entrainements getSaisie() {
        return saisie;
    }

    public void setSaisie(Entrainements saisie) {
        this.saisie = saisie;
    }         
    
    /**
     *Ajout d'un nouvel exercice
     * @param prog
     * @param c
     */
    public void saveEntrainement(Programmes prog, Comptes c) {
        saisie.setIdExercice(exerciceDAO.getExoByName(maVariable));
        saisie.setIdProgramme(prog);
        saisie.setLogin(c);
        
        saisie.setDateEntrainement(today);
        entrainementsDAO.saveEntrainement(saisie);
        FacesMessage msg = new FacesMessage("Successful", "Ajout du : " + saisie.getDateEntrainement() +" réalisé");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }
    
    /**
     * modification des exercice via le Edit
     * @param event
     */   
    public void onRowEdit(RowEditEvent event) {
        Entrainements ent = (Entrainements) event.getObject();
                System.out.println("************"+ ent.getDateEntrainement()  +"************");
        entrainementsDAO.updateEntrainement(ent);
        FacesMessage msg = new FacesMessage("Modification effectuée ! ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
     public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
     /**
     * suppression
     * @param ent 
     */
    public void suppEntrainement(Entrainements ent){ 
        this.entrainementsDAO.suppEntrainement(ent);
        listeEntrainements.remove(ent);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Niveau supprimé"));
        }

    public List<Entrainements> getEntrainementsByDateAndByProgrammes(Date d, int prog){
        return entrainementsDAO.getEntrainementsByDateAndByProgrammes(d, prog);
    }
    
        public List<Entrainements> getEntrainementsByProgrammes(int prog){
        return entrainementsDAO.getEntrainementsByProgrammes(prog);
    }
    
        public int getCountByProgrammesAndExercices(int prog, int exo){
            return entrainementsDAO.getCountByProgrammesAndExercices(prog, exo);
        }

    public LineChartModel getDateModel() {
        return dateModel;
    }

    public void setDateModel(LineChartModel dateModel) {
        this.dateModel = dateModel;
    }
    
    public List<Entrainements> getEntrainementByLogin (String login){
        return entrainementsDAO.getEntrainementByLogin(login);
    }
    
    
        public void initialiserGraphic(int prog){
        dateModel = new LineChartModel();
        listeExoByProgrammes = exerciceDAO.getExoByProgrammes(prog);
        LineChartSeries series[] = new LineChartSeries[listeExoByProgrammes.size()]; 
        
            for(int j = 0; j < listeExoByProgrammes.size(); j++){
                series[j] = new LineChartSeries();
                series[j].setLabel(listeExoByProgrammes.get(j).getNomExercice());
                listeEntrainementsByExos = entrainementsDAO.getEntrainementsByProgrammesAndExercices(prog, listeExoByProgrammes.get(j).getIdExercice());
                countEntrainementsByExos = entrainementsDAO.getCountByProgrammesAndExercices(prog, listeExoByProgrammes.get(j).getIdExercice());
                
                for(int k = 0; k < countEntrainementsByExos; k ++){
                    Date theDate = listeEntrainementsByExos.get(k).getDateEntrainement();
                    String theDateFinal = new SimpleDateFormat("yyyy-MM-dd").format(theDate);
                    Number nombreRepet = (Number) listeEntrainementsByExos.get(k).getIdEntrainements();
                    series[j].set(theDateFinal, nombreRepet);
                }
                dateModel.addSeries(series[j]);
            }      
        
        dateModel.getAxis(AxisType.Y).setLabel("Nombre de répétitions");
        DateAxis axis = new DateAxis("Dates");
         
        dateModel.getAxes().put(AxisType.X, axis);
     
    }
    
        
}
