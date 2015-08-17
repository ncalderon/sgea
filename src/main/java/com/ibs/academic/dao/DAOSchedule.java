package com.ibs.academic.dao;

import com.ibs.academic.models.Schedule;
import com.ibs.academic.models.Subject;
import com.mongodb.Mongo;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nathaniel on 4/5/14.
 */
public class DAOSchedule extends BasicDAO<Schedule,ObjectId>{
    // Implementacion de singleton

    private static final long serialVersionUID = 1L;

    private static class DAOScheduleImplHolder {
        public static final DAOSchedule INSTANCE = new DAOSchedule();
    }

    public static DAOSchedule getInstance(){
        return DAOScheduleImplHolder.INSTANCE;

    }

    protected Object readResolve(){
        return getInstance();
    }

    private DAOSchedule() {
        this(MongoDataAccess.getMongo(), MongoDataAccess.getMorphia());
    }

    private DAOSchedule(Mongo mongo, Morphia morphia) {
        super(mongo, morphia, MongoDataAccess.getNameDB());
    }

    // fin de implementacion de singleton

}
