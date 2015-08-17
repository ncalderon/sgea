package com.ibs.academic.dao;

import com.ibs.academic.models.Config;
import com.mongodb.Mongo;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.List;

/**
 * Created by nathaniel on 4/9/14.
 */
public class DAOConfig extends BasicDAO<Config,ObjectId> {
    // Implementacion de singleton

    private static final long serialVersionUID = 1L;

    private static class DAOConfigHolder {
        public static final DAOConfig INSTANCE = new DAOConfig();
    }

    public static DAOConfig getInstance(){
        return DAOConfigHolder.INSTANCE;

    }

    protected Object readResolve(){
        return getInstance();
    }

    private DAOConfig() {
        super(MongoDataAccess.getMongo(), MongoDataAccess.getMorphia(), MongoDataAccess.getNameDB());

        // fin de implementacion de singleton
    }

    public Config findByName (String name){
        return findOne("name", name);
    }

    public List<Config> listAllConfigs(){
        return find().asList();
    }


}
