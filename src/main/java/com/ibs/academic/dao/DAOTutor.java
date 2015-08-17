package com.ibs.academic.dao;

import com.ibs.academic.models.Tutor;
import com.mongodb.Mongo;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.List;

/**
 * Created by Josermando on 04/10/14.
 */
public class DAOTutor extends BasicDAO<Tutor, ObjectId> {
    //Singleton Implementation
    private static final long serialVersionUID = 1L;

    private static class DAOTutorHolder{
        public static final DAOTutor INSTANCE = new DAOTutor();
    }
    public static DAOTutor getInstance(){
        return DAOTutorHolder.INSTANCE;
    }
    protected Object readResolver(){
        return getInstance();
    }
    private DAOTutor(){
        this(MongoDataAccess.getMongo(), MongoDataAccess.getMorphia());
    }

    private DAOTutor(Mongo mongo, Morphia morphia){
        super(mongo, morphia, MongoDataAccess.getNameDB());
    }
    //End of Singleton Implementation

    public List<Tutor> listAllTutors(){
       return find().asList();
    }
}
