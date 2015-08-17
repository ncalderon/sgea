package com.ibs.academic.dao;

import com.ibs.academic.models.Period;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.List;

/**
 * Created by nathaniel on 4/9/14.
 */
public class DAOPeriod extends BasicDAO<Period, ObjectId> {
    private static final long serialVersionUID = 1L;

    private static class DAOPeriodHolder {
        public static final DAOPeriod INSTANCE = new DAOPeriod();
    }

    public static DAOPeriod getInstance(){
        return DAOPeriodHolder.INSTANCE;

    }

    protected Object readResolve(){
        return getInstance();
    }

    private DAOPeriod() {
        super(MongoDataAccess.getMongo(), MongoDataAccess.getMorphia(), MongoDataAccess.getNameDB());
    }

    // fin de implementacion de singleton
    public List<Period> listPeriod(){
        return find().asList();
    }

}
