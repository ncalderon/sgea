package com.ibs.academic.models;

import org.mongodb.morphia.annotations.Entity;

/**
 * Created by nathaniel on 4/9/14.
 */

@Entity("config")
public class Config extends MongoObject{
    private String name;
    private String value;

    public Config() {

    }

    public Config(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
