package com.ibs.academic.controllers;

import com.ibs.academic.controllers.DataModels.EmployeeDataModel;
import com.ibs.academic.dao.DAOEmployee;
import com.ibs.academic.dao.DAORole;
import com.ibs.academic.dao.DAOUser;
import com.ibs.academic.models.*;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josermando on 03/29/14.
 */
@ManagedBean(name="employeeBean")
@ViewScoped
public class EmployeeBean implements Serializable{
    private List<Employee> employees;
    private DAOEmployee daoEmployee;
    private Employee employeeItem;
    // mantenimiento de telefono
    private List<Phone> employeePhones;
    private Phone phoneItem;
    // mantenimiento de horarios disponibles
    private List<Schedule> employeeSchedules;
    private Schedule scheduleItem;

    @PostConstruct
    public void init(){
        employeeItem = new Employee();
        daoEmployee = DAOEmployee.getInstance();
        setEmployees(daoEmployee.find().asList());
        // logica de telefonos
        phoneItem = new Phone();
        employeePhones = new ArrayList<Phone>();
        // logica de horarios disponibles
        scheduleItem = new Schedule();
        employeeSchedules = new ArrayList<Schedule>();
    }

    public void addSchedule (){
        employeeSchedules.add(scheduleItem);
        scheduleItem = new Schedule();
    }

    public void deleteSchedule (Schedule item){
        employeeSchedules.remove(item);

    }

    public void addPhone (){
        employeePhones.add(phoneItem);
        phoneItem = new Phone();
    }

    public void deletePhone (Phone item){
        employeePhones.remove(item);

    }

    public void refreshItem (CloseEvent event){
        setEmployeeItem(new Employee());
    }

    public void add(){
        employeeItem.setPhones(employeePhones);
        employeeItem.setScheduleAvailable(employeeSchedules);
        daoEmployee.save(employeeItem);
        setEmployees(daoEmployee.find().asList());
        employeeItem = new Employee();

    }

    public void delete(Employee item){
        daoEmployee.delete(item);
        employees.remove(item);
    }

    //Getters and setters...
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Employee getEmployeeItem() {
        return employeeItem;
    }

    public void setEmployeeItem(Employee employeeItem) {
        this.employeeItem = employeeItem;
        setEmployeePhones(employeeItem.getPhones());
        setEmployeeSchedules(employeeItem.getScheduleAvailable());

    }

    public List<Phone> getEmployeePhones() {
        return employeePhones;
    }

    public void setEmployeePhones(List<Phone> employeePhones) {
        this.employeePhones = employeePhones;
    }

    public Phone getPhoneItem() {
        return phoneItem;
    }

    public void setPhoneItem(Phone phoneItem) {
        this.phoneItem = phoneItem;
    }

    public List<Schedule> getEmployeeSchedules() {
        return employeeSchedules;
    }

    public void setEmployeeSchedules(List<Schedule> employeeSchedules) {
        this.employeeSchedules = employeeSchedules;
    }

    public Schedule getScheduleItem() {
        return scheduleItem;
    }

    public void setScheduleItem(Schedule scheduleItem) {
        this.scheduleItem = scheduleItem;
    }
}
