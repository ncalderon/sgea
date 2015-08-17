package com.ibs.academic;

import com.ibs.academic.controllers.LoginBean;
import com.ibs.academic.controllers.UserBean;
import com.ibs.academic.dao.*;
import com.ibs.academic.models.*;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Angel Rene Garcia on 03/04/14.
 */
public class Dummy {
    public static void main(String[] args) {
//        LoginBean loginBean = new LoginBean();
//        System.out.println("1 "+loginBean.allEmployees());
//        System.out.println("2 "+loginBean.allStudents());
        testResults();
        System.exit(0);

    }

    public static void testResults (){

       DAOEmployee daoEmployee = DAOEmployee.getInstance();
       Employee employee = daoEmployee.get(new ObjectId("5348c6168c49580b805788c3"));
       DAOSubjectCourse daoSubjectCourse = DAOSubjectCourse.getInstance();
       //daoSubjectCourse.findByTeacher(employee);

    }

    public static void testSubject(){
        DAOSubject daoSubject =  DAOSubject.getInstance();
        Subject subject = new Subject();
        subject.setName("test");
        daoSubject.save(subject);

    }

    public static void testPeriod (){
        DAOPeriod daoPeriod = DAOPeriod.getInstance();
        List<Period> ls = daoPeriod.find().asList();

    }



    public static void testAsistencia(){

     /*   DAOTransaction daoTransaction = DAOTransaction.getInstance();
        DAOStudent daoStudent = DAOStudent.getInstance();
        Student student = daoStudent.get(new ObjectId("5340b6732e1f71db9891b11b"));
        DAOStudentPeriod daoStudentPeriod = DAOStudentPeriod.getInstance();
        StudentPeriod studentPeriod = daoStudentPeriod.findOne("id_student", student.getId());
        List<PeriodSubject> periodSubjects = studentPeriod.getPeriodSubject();
        Transaction transaction =  new Transaction();
        transaction.setIdStudent(student);

        transaction.setIdTransaction(periodSubjects.get(0).getId());
        transaction.setTypeTransaction("registro_asistencia");
        transaction.setValue("1");
        daoTransaction.save(transaction);
*/
    }

}
