package com.ibs.academic.dao;

import com.ibs.academic.models.Role;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.List;

/**
 * Created by Josermando on 03/25/14.
 */
public class DAORole extends BasicDAO<Role, ObjectId> {
    //Singleton Implementation
    private static final long serialVersionUID = 1L;

    private DAORole(){
       super(MongoDataAccess.getDatastore());
    }

    private static class DAORoleHolder{
        private static final DAORole INSTANCE = new DAORole();
    }

    public static DAORole getInstance(){
        return DAORoleHolder.INSTANCE;
    }

    protected Object readResolve(){
        return getInstance();
    }

    //End of Singleton Implementation

    public List<Role> listAllRoles(){
        return find().asList();
    }

    public List<Role> listRole(String item){
        return createQuery().field("name").equal(item).asList();
    }
}
