package com.ibs.academic.dao;

import com.ibs.academic.models.Course;
import com.ibs.academic.models.Employee;
import com.ibs.academic.models.Student;
import com.ibs.academic.models.StudentCourse;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Josermando on 03/25/14.
 */
public class DAOStudent extends BasicDAO<Student, ObjectId> {
    //Singleton Implementation
    private static final long serialVersionUID = 1L;

    private DAOStudent(){
        super(MongoDataAccess.getDatastore());
    }

    private static class DAOStudentHolder{
        private static final DAOStudent INSTANCE = new DAOStudent();
    }

    public static DAOStudent getInstance(){
        return DAOStudentHolder.INSTANCE;
    }

    protected Object readResolve(){
        return getInstance();
    }
    //End of Singleton Implementation

    public List<Student> getAllStudents(){
        return find().asList();
    }

    public List<Student> findByName (String name){
        Query<Student> query = createQuery().field("status").equal("active");
        query.and(
                query.or(
                        query.criteria("firstname").equal(Pattern.compile(name, Pattern.CASE_INSENSITIVE)),
                        query.criteria("lastname").equal(Pattern.compile(name, Pattern.CASE_INSENSITIVE))
                )
        );
        return query.asList();

    }

    public Course getCurrentCourse(Student es){
        Student student = es;//findOne("_id", new ObjectId("53484f0fd1eab7925b36d840"));

        Course course = new Course();
        if (student.getStudentCourses() != null){

            for (int i = 0; i < student.getStudentCourses().size(); i++) {
                StudentCourse studentCourse =  student.getStudentCourses().get(i);
                if(studentCourse.getStatus().equalsIgnoreCase("active")){
                    course = studentCourse.getCourse();
                }

            }
        }

        return course;
    }

}
