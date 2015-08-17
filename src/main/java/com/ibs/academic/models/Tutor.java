package com.ibs.academic.models;

import org.mongodb.morphia.annotations.Entity;

import java.util.List;

/**
 * Created by nathaniel on 3/19/14.
 */

@Entity("tutor")
public class Tutor extends Person{

    public Tutor() {
        super();
    }

    public Tutor(String firstname, String lastname, String identification, String typeIdentification, String status, List<Phone> phones) {
        super(firstname, lastname, identification, typeIdentification, status, phones);
    }
}
