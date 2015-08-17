package com.ibs.academic.models;

import org.mongodb.morphia.annotations.Embedded;

import java.util.List;

/**
 * Created by nathaniel on 3/19/14.
 */
public abstract class Person extends MongoObject{
    private String firstname;
    private String lastname;
    private String identification;
    private String typeIdentification;
    // status = active | inactive
    private String status;

    // TODO: IMPLEMENTAR DESPUES, POR AHORA NO SE VA GUARDAR
    @Embedded("phones")
    private List<Phone> phones;

    protected Person() {

    }

    protected Person(String firstname, String lastname, String identification, String typeIdentification, String status, List<Phone> phones) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.identification = identification;
        this.typeIdentification = typeIdentification;
        this.status = status;
        this.phones = phones;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getTypeIdentification() {
        return typeIdentification;
    }

    public void setTypeIdentification(String typeIdentification) {
        this.typeIdentification = typeIdentification;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
}
