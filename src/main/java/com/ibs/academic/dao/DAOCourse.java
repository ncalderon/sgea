package com.ibs.academic.dao;

import com.ibs.academic.models.Course;
import com.mongodb.Mongo;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.List;

/**
 * Created by Josermando on 03/25/14.
 */
public class DAOCourse extends BasicDAO<Course, ObjectId> {
    //Singleton Implementation
    private static final long serialVersionUID = 1L;
    private static class DAOCourseHolder{
        public static final DAOCourse INSTANCE = new DAOCourse();
    }

    public static DAOCourse getInstance(){
        return DAOCourseHolder.INSTANCE;
    }

    protected Object readResolve(){
        return DAOCourse.getInstance();
    }

    private DAOCourse(){
        this(MongoDataAccess.getMongo(), MongoDataAccess.getMorphia());
    }

    private DAOCourse(Mongo mongo, Morphia morphia){
        super(mongo,morphia,MongoDataAccess.getNameDB());
    }

    //End of Singleton Implementation



}
