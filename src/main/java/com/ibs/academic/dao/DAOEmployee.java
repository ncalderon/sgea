package com.ibs.academic.dao;

import com.ibs.academic.models.Employee;
import com.ibs.academic.models.Schedule;
import com.ibs.academic.models.Tutor;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Josermando on 03/25/14.
 */
public class DAOEmployee extends BasicDAO<Employee, ObjectId> {
    //Singleton Implementation
    private static final long serialVersionUID = 1L;

    private DAOEmployee(){
        super(MongoDataAccess.getDatastore());
    }

    private static class DAOEmployeeHolder{
        private static final DAOEmployee INSTANCE = new DAOEmployee();
    }

    public static DAOEmployee getInstance(){
        return DAOEmployeeHolder.INSTANCE;
    }

    protected Object readResolve(){
        return getInstance();
    }
    //End of Singleton Implementation

    public List<Employee> listAllEmployees(){
        return find().asList();
    }

    public void resetReservedHours(){
        Query<Employee> query = createQuery().field("status").equal("active");
        query.and(
                query.criteria("type_employee").equal("teacher")
        );
        List<Employee> employees = find(query).asList();
        List<Schedule> schedules;
        for(int i = 0; i < employees.size(); i++){
            schedules = employees.get(i).getScheduleAvailable();
            for(int j = 0; j < schedules.size(); j++){
                schedules.get(j).setHours_reserved(0);
            }
            employees.get(i).setScheduleAvailable(schedules);
            save(employees.get(i));
        }
        //UpdateOperations<Schedule> ops = createUpdateOperations(Schedule).set("schedule_available.$.hours_reserved", 0);
        //update(query, ops);
    }

    public List<Employee> findTeacherByName (String name){
        Query<Employee> query = createQuery().field("type_employee").equal("teacher").field("status").equal("active");
        query.or(
                query.criteria("firstname").equal(Pattern.compile(name, Pattern.CASE_INSENSITIVE)),
                query.criteria("lastname").equal(Pattern.compile(name, Pattern.CASE_INSENSITIVE))
        );
        return query.asList();
    }
}
