package com.ibs.academic.dao;

import com.ibs.academic.models.Employee;
import com.ibs.academic.models.Schedule;
import com.ibs.academic.models.Subject;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Angel Rene Garcia on 03/24/14.
 */
public class DAOSubject extends BasicDAO<Subject, ObjectId> implements Serializable{

    private static final long serialVersionUID = 1L;

    private static class DAOSubjectHolder {
        public static final DAOSubject INSTANCE = new DAOSubject();
    }

    public static DAOSubject getInstance(){
        return DAOSubjectHolder.INSTANCE;

    }

    protected Object readResolve(){
        return getInstance();
    }

    private DAOSubject() {
        super(MongoDataAccess.getMongo(), MongoDataAccess.getMorphia(), MongoDataAccess.getNameDB());
    }

    public ObjectId addSubject(Subject subject){
        return (ObjectId) save(subject).getId();
    }
    public List<Subject> listAllSubjects(){
       return find().asList();
    }


    public List<Subject> listActiveSubjects(){
        return createQuery().field("status").equal("active").asList();
    }

    public List<Subject> findSubjectByName (String name){
        Query<Subject> query = createQuery().field("status").equal("active");
        query.and(
                query.criteria("name").equal(Pattern.compile(name, Pattern.CASE_INSENSITIVE))
        );
        return query.asList();
    }



}