package com.ibs.academic.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

import java.util.Date;

/**
 * Created by nathaniel on 3/20/14.
 */

@Entity("period")
public class Period extends MongoObject{
    private String name;
    private Date dateStarted;
    private Date dateEnd;
    private ObjectId parent;
    private String status;

    public Period() {

    }

    public Period(String name, Date dateStarted, Date dateEnd, ObjectId parent, String status) {
        this.name = name;
        this.dateStarted = dateStarted;
        this.dateEnd = dateEnd;
        this.parent = parent;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public ObjectId getParent() {
        return parent;
    }

    public void setParent(ObjectId parent) {
        this.parent = parent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
