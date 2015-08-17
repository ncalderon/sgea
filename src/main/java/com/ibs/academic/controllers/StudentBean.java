package com.ibs.academic.controllers;

import com.ibs.academic.dao.DAOStudent;
import com.ibs.academic.dao.DAOTutor;
import com.ibs.academic.models.Address;
import com.ibs.academic.models.Phone;
import com.ibs.academic.models.Student;
import com.ibs.academic.models.Tutor;
import org.mongodb.morphia.query.Query;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Josermando on 03/29/14.
 */

// test
@ManagedBean(name="studentBean")
@ViewScoped
public class StudentBean implements Serializable{
    private List<Student> studentList;

    // mantenimiento de telefono
    private List<Phone> studentPhones;
    private Phone phoneItem;
    //Mantenimiento de Direccion
    private List<Address> studentAddress;
    private Address addressItem;

    private Student studentItem;
    private DAOStudent daoStudent;
    private boolean updateItem;

    @PostConstruct
    public void init(){
        studentItem = new Student();
        daoStudent = DAOStudent.getInstance();
        studentList = daoStudent.getAllStudents();

        // logica de telefonos
        phoneItem = new Phone();
        studentPhones = new ArrayList<Phone>();

        //Logica de Direccion
        addressItem = new Address();
        studentAddress = new ArrayList<Address>();
        /*if(studentItem.getPhones().isEmpty()){

        }else{
            studentPhones = studentItem.getPhones();
        }*/


    }

    public void addPhone (){
        studentPhones.add(phoneItem);
        phoneItem = new Phone();
    }

    public void deletePhone (Phone item){
        studentPhones.remove(item);

    }

    public void addAddress(){
        studentAddress.add(addressItem);
        addressItem = new Address();
    }

    public void deleteAddress(Address item){
        studentAddress.remove(item);
    }

    public void newItem (){
        studentItem = new Student();

    }

    public void add(){
        studentItem.setPhones(studentPhones);
        studentItem.setAddress(studentAddress);
        daoStudent.save(studentItem);

        setStudentList(daoStudent.find().asList());
        studentItem = new Student();
        setUpdateItem(false);
    }

    public void save(){
        studentItem.setPhones(studentPhones);
        studentItem.setAddress(studentAddress);
        daoStudent.save(studentItem);
        studentItem = new Student();
        setUpdateItem(false);
    }

    public void delete(Student studentItem){
        daoStudent.delete(studentItem);
        studentList.remove(studentItem);
        setUpdateItem(false);
    }

    public void edit(Student item){
        setStudentItem(item);
        setUpdateItem(true);
    }

    public List<Tutor> findTutor(String name){
        DAOTutor daoTutor = DAOTutor.getInstance();
        Query<Tutor> query = daoTutor.getDs().createQuery(Tutor.class);
        query.or(
                query.criteria("firstname").equal(Pattern.compile(name, Pattern.CASE_INSENSITIVE)),
                query.criteria("lastname").equal(Pattern.compile(name, Pattern.CASE_INSENSITIVE))
        );
        return query.asList();
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

    public void tutorSelected(SelectEvent event) {
        Tutor tutor = (Tutor)event.getObject();
        studentItem.setTutor(tutor);
        /* Josermando! y esta vaina aqui! lo comenté!
        if (tutor.getFirstname() == null || tutor.getFirstname().equals("0")) {
            studentItem.setAcademicStatus("1");
        } else if (tutor.getFirstname().equals("1")){
            studentItem.setAcademicStatus("2");
        } else if (tutor.getFirstname().equals("2")){
            studentItem.setAcademicStatus("3");
        }
        */

    }

    public void onEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Estudiante Editado", ((Student) event.getObject()).getFirstname());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edicion Cancelada", ((Student) event.getObject()).getFirstname());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    //Getter & Setters

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public Student getStudentItem() {
        return studentItem;
    }

    public void setStudentItem(Student studentItem) {
        this.studentItem = studentItem;
        setStudentPhones(studentItem.getPhones());
        setStudentAddress(studentItem.getAddress());
    }

    public boolean isUpdateItem() {
        return updateItem;
    }

    public void setUpdateItem(boolean isEditable) {
        this.updateItem = isEditable;
    }

    public Phone getPhoneItem() {
        return phoneItem;
    }

    public void setPhoneItem(Phone phoneItem) {
        this.phoneItem = phoneItem;
    }

    public List<Phone> getStudentPhones() {
        return studentPhones;
    }

    public void setStudentPhones(List<Phone> studentPhones) {
        this.studentPhones = studentPhones;
    }

    public List<Address> getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(List<Address> studentAddress) {
        this.studentAddress = studentAddress;
    }

    public Address getAddressItem() {
        return addressItem;
    }

    public void setAddressItem(Address addressItem) {
        this.addressItem = addressItem;
    }
}
