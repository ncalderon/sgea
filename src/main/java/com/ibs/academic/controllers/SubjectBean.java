package com.ibs.academic.controllers;

import com.ibs.academic.dao.DAOSubject;
import com.ibs.academic.models.Subject;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Angel Rene Garcia on 03/25/14.
 */
@ManagedBean(name="subjectBean")
@ViewScoped
public class SubjectBean implements Serializable {
    private List<Subject> list;
    private Subject item;
    private DAOSubject daoSubject;

    @PostConstruct
    public void init() {
        item = new Subject();
        daoSubject = DAOSubject.getInstance();
        list = daoSubject.find().asList();

    }

    public void newItem (){
        item = new Subject();
    }
    public void deleteRow (Subject item){
        daoSubject.delete(item);
        list.remove(item);
    }

    public void add() {
        daoSubject.save(item);
        list = daoSubject.find().asList();
        item = new Subject(); // Reset placeholder.
    }


    public void edit(Subject item) {
        this.item = item;
    }

    public Subject getItem() {
        return item;
    }

    public void setItem(Subject item) {
        this.item = item;
    }

    public List<Subject> getList() {
        return list;
    }

    public void setList(List<Subject> list) {
        this.list = list;
    }


}
