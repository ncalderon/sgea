package com.ibs.academic.controllers;

import com.ibs.academic.controllers.DataModels.PeriodDataModel;
import com.ibs.academic.dao.DAOPeriod;
import com.ibs.academic.models.Period;
import org.bson.types.ObjectId;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by nathaniel on 4/2/14.
 */
// test test
@ManagedBean(name = "periodBean")
@ViewScoped
public class PeriodBean implements Serializable{

    private List<Period> periodList;
    private Period periodItem;
    private DAOPeriod daoPeriod;
    /*private PeriodDataModel periodDataModel;*/
    
    @PostConstruct
    public void init(){
        daoPeriod = DAOPeriod.getInstance();
        setPeriodList(daoPeriod.find().asList());
        setPeriodItem(new Period());
        /*periodDataModel = new PeriodDataModel(getPeriodList());*/
    }

/*
    public PeriodDataModel getPeriodDataModel() {
        return periodDataModel;
    }

    public void setPeriodDataModel(PeriodDataModel periodDataModel) {
        this.periodDataModel = periodDataModel;
    }
*/

    public List<Period> getPeriodList() {
        return periodList;

    }

    /*public void onRowSelect(SelectEvent event) {
        periodItem = ((Period) event.getObject());
        //FacesMessage msg = new FacesMessage("Periodo Seleccionada", ((Period) event.getObject()).getName());
        //FacesContext.getCurrentInstance().addMessage(null, msg);
    }*/

    /*public void onRowUnselect(UnselectEvent event) {
        periodItem = new Period();
        FacesMessage msg = new FacesMessage("Periodo deseleccionado", ((Period) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }*/


    public void setPeriodList(List<Period> periodList) {
        this.periodList = periodList;
    }

    public Period getPeriodItem() {
        return periodItem;
    }

    public void setPeriodItem(Period periodItem) {
        this.periodItem = periodItem;
    }

  /*  public void resetDialog() {
        //RequestContext.getCurrentInstance().execute("formatearSwitch()");
        RequestContext.getCurrentInstance().reset(":containerForm:modal_dialog");
    }

    public void resetTabla() {
    //RequestContext.getCurrentInstance().execute("formatearSwitch()");
    RequestContext.getCurrentInstance().reset(":containerForm:tblperiodos");
    }*/

    public void edit (){
        daoPeriod.save(periodItem);
        setPeriodList(daoPeriod.find().asList());
        //resetDialog();
        //resetTabla();
        periodItem = new Period();
    }


    public void add (){
        daoPeriod.save(periodItem);
        setPeriodList(daoPeriod.find().asList());
        //resetDialog();
        //resetTabla();
        periodItem = new Period();
    }

    public void deleteRow (Period item){
        daoPeriod.delete(item);
        periodList.remove(item);
    }

}
