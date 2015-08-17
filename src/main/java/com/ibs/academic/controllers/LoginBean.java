package com.ibs.academic.controllers;

import com.ibs.academic.dao.DAOEmployee;
import com.ibs.academic.dao.DAOStudent;
import com.ibs.academic.dao.DAOUser;
import com.ibs.academic.models.Employee;
import com.ibs.academic.models.Student;
import com.ibs.academic.models.User;
import org.mongodb.morphia.query.Query;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Josermando on 04/12/14.
 */
@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean implements Serializable{
    private String username;
    private String password;
    private DAOUser daoUser;
    private boolean loggedIn;
    private User currentUser;
    private String msj;

    @PostConstruct
    public void init(){
        daoUser = DAOUser.getInstance();
        setCurrentUser(getCurrentUser());

    }

    public String authentication(){
        daoUser.listAllUsers();
        Query<User> query = daoUser.getDs().createQuery(User.class);
        query.and(
                query.criteria("username").equal(getUsername()),
                query.criteria("password").equal(getPassword())
        );

        if(query.asList().size() > 0){
            setMsj("");
            setLoggedIn(true);
            setCurrentUser(query.asList().get(0));
            return "/index.xhtml?faces-redirect=true";
        }else {
            setMsj("Usuario / Pass Incorrectos");
            return "/views/login.xhtml";
        }
//        else {
//            setLoggedIn(false);
//            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Credenciales Invalidas");
//        }




    }

    public String logout(){
        setCurrentUser(new User());
        setPassword("");
        setUsername("");
        setLoggedIn(false);
        return "/views/login.xhtml?faces-redirect=true";

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String getMsj() {
        return msj;
    }

    public void setMsj(String msj) {
        this.msj = msj;
    }
}


