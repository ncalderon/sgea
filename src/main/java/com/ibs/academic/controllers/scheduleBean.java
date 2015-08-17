package com.ibs.academic.controllers;

import com.google.gson.Gson;
import com.ibs.academic.dao.DAOCourse;
import com.ibs.academic.dao.DAOStudent;
import com.ibs.academic.dao.DAOTransaction;
import com.ibs.academic.models.Course;
import com.ibs.academic.models.Transaction;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Angel Rene Garcia on 04/10/14.
 */
@ManagedBean(name="scheduleBean")
@ViewScoped
public class scheduleBean implements Serializable{

    private DAOStudent daoStudent;
    private Course course;
    @ManagedProperty("#{loginBean}")
    private LoginBean login;
    @PostConstruct
    public void init(){
        daoStudent = DAOStudent.getInstance();
        setCourse(daoStudent.getCurrentCourse(login.getCurrentUser().getStudent()));
    }
    public String getJsonList(){
        return new Gson().toJson(getCourse());
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LoginBean getLogin() {
        return login;
    }

    public void setLogin(LoginBean login) {
        this.login = login;
    }
}
