/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entrainements;

import Comptes.Comptes;
import Exercices.Exercices;
import Exercices.ExercicesController;
import Exercices.ExercicesDAO;
import Programmes.Programmes;
import Programmes.ProgrammesDAO;
import java.io.Serializable;
import java.time.Instant;
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
    private Exercices myExercice;
    
    private Comptes myCompte;
   
    private Calendar calendar;
    private Calendar calendar2;
  
    private Date today;
    private Date yesterday;
    
   
    
    private Entrainements saisie;
    private String maVariable;
    private List<Entrainements> listeEntrainements;
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
    
       /* public void initialiserGraphic(int prog){
        dateModel = new LineChartModel();
        
        for(int )
        
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");
 
        series1.set("2014-01-01", 51);
        series1.set("2014-01-06", 22);
        series1.set("2014-01-12", 65);
        series1.set("2014-01-18", 74);
        series1.set("2014-01-24", 24);
        series1.set("2014-01-30", 51);
 
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");
 
        series2.set("2014-01-01", 32);
        series2.set("2014-01-06", 73);
        series2.set("2014-01-12", 24);
        series2.set("2014-01-18", 12);
        series2.set("2014-01-24", 74);
        series2.set("2014-01-30", 62);
 
        dateModel.addSeries(series1);
        dateModel.addSeries(series2);
         
        dateModel.setTitle("Zoom for Details");
        dateModel.setZoom(true);
        dateModel.getAxis(AxisType.Y).setLabel("Values");
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-50);
        axis.setMax("2014-02-01");
        axis.setTickFormat("%b %#d, %y");
         
        dateModel.getAxes().put(AxisType.X, axis);
    }*/
    
    
}
