package com.ibs.academic.controllers;

import com.ibs.academic.dao.DAOTutor;
import com.ibs.academic.models.Tutor;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Josermando on 04/10/14.
 */
@ManagedBean(name="tutorBean")
@ViewScoped
public class TutorBean implements Serializable {
    private List<Tutor> tutorList;
    private Tutor item;
    private DAOTutor daoTutor;
    private boolean updateItem;

    @PostConstruct
    public void init(){
        item = new Tutor();
        daoTutor = DAOTutor.getInstance();
        tutorList = daoTutor.listAllTutors();
    }

    public void add(){
        daoTutor.save(item);
        tutorList.add(item);
        item = new Tutor();
        setUpdateItem(false);
    }

    public void save(){
        daoTutor.save(item);
        item = new Tutor();
        setUpdateItem(false);
    }

    public void delete(Tutor item){
        daoTutor.delete(item);
        tutorList.remove(item);
        setUpdateItem(false);
    }

    public void edit(Tutor item){
        this.item = item;
        setUpdateItem(true);
    }

    public List<Tutor> getTutorList() {
        return tutorList;
    }

    public void setTutorList(List<Tutor> tutorList) {
        this.tutorList = tutorList;
    }

    public Tutor getItem() {
        return item;
    }

    public void setItem(Tutor item) {
        this.item = item;
    }

    public boolean isUpdateItem() {
        return updateItem;
    }

    public void setUpdateItem(boolean updateItem) {
        this.updateItem = updateItem;
    }
}
