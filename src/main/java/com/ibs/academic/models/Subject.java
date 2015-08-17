package com.ibs.academic.models;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

import java.util.List;

/**
 * Created by nathaniel on 3/19/14.
 */

@Entity("subject")
public class Subject extends MongoObject {
    private String name;
    // status = active | inactive
    private String status;


    public Subject() {

    }

    public Subject(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
