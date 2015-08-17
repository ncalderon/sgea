package com.ibs.academic.models;

import org.mongodb.morphia.annotations.Entity;

/**
 * Created by nathaniel on 3/19/14.
 */

@Entity("role")
public class Role extends MongoObject {
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
