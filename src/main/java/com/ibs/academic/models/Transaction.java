package com.ibs.academic.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;
import org.primefaces.context.RequestContext;

import java.util.Date;

/**
 * Created by nathaniel on 3/23/14.
 */

@Entity("transaction")
public class Transaction extends MongoObject{
    //Tipos:
    //1:  inscripcion / reinscripcion

    //5: "registro_asistencia"
    private String typeTransaction;
    private ObjectId idTransaction;
    @Reference("idStudent")
    private Student idStudent;
    private Date date;
    private String value;
    //P: Presente
    //A: Ausente
    public Transaction() {
        this.setDate(new Date());
    }

    public Transaction(String typeTransaction, Student idStudent, String value) {
        this();
        this.typeTransaction = typeTransaction;
        this.idStudent = idStudent;
        this.value = value;
    }

    public ObjectId getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(ObjectId idTransaction) {
        this.idTransaction = idTransaction;
    }

    public String getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(String typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public Student getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Student idStudent) {
        this.idStudent = idStudent;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

