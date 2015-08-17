package com.ibs.academic.dao;

import com.ibs.academic.models.Result;
import com.mongodb.Mongo;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

/**
 * Created by Josermando on 03/24/14.
 */
public class DAOResult extends BasicDAO<Result, ObjectId> {

    //Singleton Implementation
    private static final long serialVersionUID = 1L;

    private DAOResult(){
        super(MongoDataAccess.getDatastore());
    }

    private static class DAOResultHolder{
        public static final DAOResult INSTANCE = new DAOResult();
    }

    public static DAOResult getInstance(){
        return DAOResultHolder.INSTANCE;
    }

    protected Object readResolve(){
        return getInstance();
    }
    //End Of Singleton Implementation


}
