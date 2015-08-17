package com.ibs.academic.dao;

import com.ibs.academic.models.Role;
import com.ibs.academic.models.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.List;

/**
 * Created by Josermando on 03/24/14.
 */
public class DAOUser extends BasicDAO<User, ObjectId> {

    //Singleton Implementation
    private static final long serialVersionUID = 1L;

    private DAOUser(){
        super(MongoDataAccess.getDatastore());
    }

    private static class DAOUserImpHolder{
        public static final DAOUser INSTANCE = new DAOUser();
    }

    public static DAOUser getInstance(){
        return DAOUserImpHolder.INSTANCE;
    }

    protected Object readResolve(){
        return getInstance();
    }
    //End of Singleton Implementation

    public List<User> listAllUsers(){
        return find().asList();
    }

    public List<Role> getRolesAvailable() {
        DAORole roleList = DAORole.getInstance();
        return roleList.listAllRoles();
//        return ds.createQuery(Role.class).field("name").equal(null).asList();
    }

}
