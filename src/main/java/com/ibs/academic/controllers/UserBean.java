package com.ibs.academic.controllers;

import com.ibs.academic.dao.DAOEmployee;
import com.ibs.academic.dao.DAOStudent;
import com.ibs.academic.dao.DAOUser;
import com.ibs.academic.models.Employee;
import com.ibs.academic.models.Role;
import com.ibs.academic.models.Student;
import com.ibs.academic.models.User;
import org.mongodb.morphia.query.Query;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Josermando on 03/28/14.
 */
@ManagedBean(name="userBean")
@ViewScoped
public class UserBean implements Serializable {
    private List<User> userList;
    private List<Role> roles;
    private Role role;
    private User userItem;
    private DAOUser daoUser;
    private boolean updateItem;

    @PostConstruct
    public void init(){
        userItem = new User();
        daoUser = DAOUser.getInstance();
        userList = daoUser.listAllUsers();
        roles = daoUser.getRolesAvailable();

    }
    public List<Role> getRoles() {

        return roles;
    }

    public void add(){
        daoUser.save(userItem);
        userList.add(userItem);
        userItem = new User();
        setUpdateItem(false);
    }

    public void save(){
        daoUser.save(userItem);
        userItem = new User();
        setUpdateItem(false);
    }

    public void delete(User userItem){
        daoUser.delete(userItem);
        userList.remove(userItem);
        setUpdateItem(false);
    }

    public void edit(User userItem){
        this.userItem = userItem;
        setUpdateItem(true);
    }

    public List<Student> findStudent(String name){
        DAOStudent daoStudent = DAOStudent.getInstance();
        Query<Student> query = daoStudent.getDs().createQuery(Student.class);
        query.or(
                query.criteria("firstname").equal(Pattern.compile(name, Pattern.CASE_INSENSITIVE)),
                query.criteria("lastname").equal(Pattern.compile(name, Pattern.CASE_INSENSITIVE))
        );
        return query.asList();
    }

    public List<Employee> findEmployee(String name){
        DAOEmployee daoEmployee = DAOEmployee.getInstance();
        Query<Employee> query = daoEmployee.getDs().createQuery(Employee.class);
        query.or(
                query.criteria("firstname").equal(Pattern.compile(name, Pattern.CASE_INSENSITIVE)),
                query.criteria("lastname").equal(Pattern.compile(name, Pattern.CASE_INSENSITIVE))
        );
        return query.asList();
    }

    public void studentSelected(SelectEvent event) {
        Student student = (Student)event.getObject();
        userItem.setStudent(student);
        }

    public void employeeSelected(SelectEvent event) {
        Employee employee = (Employee)event.getObject();
        userItem.setEmployee(employee);
    }

    //Getters and Setters


    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getUserItem() {
        return userItem;
    }

    public void setUserItem(User userItem) {
        this.userItem = userItem;
    }

    public boolean isUpdateItem() {
        return updateItem;
    }

    public void setUpdateItem(boolean isEditable) {
        this.updateItem = isEditable;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
        userItem.setRole(role);
    }
}
