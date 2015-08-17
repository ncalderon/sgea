package com.ibs.academic.controllers;

import com.ibs.academic.dao.DAOCourse;
import com.ibs.academic.dao.DAOStudent;
import com.ibs.academic.dao.DAOTransaction;
import com.ibs.academic.models.Course;
import com.ibs.academic.models.Student;
import com.ibs.academic.models.StudentCourse;
import com.ibs.academic.models.Transaction;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by josesuero on 4/9/14.
 */
@ManagedBean(name="transactionBean")
public class TransactionsBean {
    private Transaction transactionItem;
    private Course courseItem;
    private ObjectId courseId;
    private DAOTransaction daoTransaction;
    private List<Transaction> transactionList;

    @PostConstruct
    public void init(){
        setTransactionItem(new Transaction());
        daoTransaction = DAOTransaction.getInstance();
        setTransactionList(daoTransaction.getTransactionsByType("1"));
    }

    public List<Student> findStudent(String name){
        DAOStudent daoStudent = DAOStudent.getInstance();
        Query<Student> query = daoStudent.getDs().createQuery(Student.class);
        query.or(
                query.criteria("firstname").equal(Pattern.compile(name, Pattern.CASE_INSENSITIVE)),
                query.criteria("lastname").equal(Pattern.compile(name, Pattern.CASE_INSENSITIVE))
                );
        return query.asList();
    }

    public List<Course> findCourse(String name){
        DAOCourse daoCourse = DAOCourse.getInstance();
        Query<Course> query = daoCourse.getDs().createQuery(Course.class);
        query.or(
                query.criteria("name").equal(Pattern.compile(name, Pattern.CASE_INSENSITIVE))
        );
        return query.asList();
    }

    public ObjectId getCourseId() {
        return courseId;
    }

    public void setCourseId(ObjectId courseId) {
        this.courseId = courseId;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public Transaction getTransactionItem() {
        return transactionItem;
    }

    public void setTransactionItem(Transaction transactionItem) {
        this.transactionItem = transactionItem;
    }

    public Course getCourseItem() {
        return courseItem;
    }

    public void setCourseItem(Course courseItem) {
        this.courseItem = courseItem;
    }

    public void studentSelected(SelectEvent event) {
        Student student = (Student)event.getObject();
        transactionItem.setIdStudent(student);
/*        if (student.getAcademicStatus() == null || student.getAcademicStatus().equals("0")) {
            transactionItem.setTypeTransaction("1");
        } else if (student.getAcademicStatus().equals("1")){
            transactionItem.setTypeTransaction("2");
        } else if (student.getAcademicStatus().equals("2")){
            transactionItem.setTypeTransaction("3");
        }*/

    }
    public void courseSelected(SelectEvent event) {
        Course course = (Course)event.getObject();
        setCourseId(course.getId());
        //transactionItem.setIdTransaction(course.getId());
/*        if (student.getAcademicStatus() == null || student.getAcademicStatus().equals("0")) {
            transactionItem.setTypeTransaction("1");
        } else if (student.getAcademicStatus().equals("1")){
            transactionItem.setTypeTransaction("2");
        } else if (student.getAcademicStatus().equals("2")){
            transactionItem.setTypeTransaction("3");
        }*/

    }

    public void add(){

        //Update Student
 /*       DAOStudent daoStudent = DAOStudent.getInstance();
        //Si no es un pago mensual pasa a estar inscrito (incripcion o reinscripcion)
        if (!transactionItem.getTypeTransaction().equals("2")) {
            transactionItem.getIdStudent().setAcademicStatus("1");
        }
        daoStudent.save(transactionItem.getIdStudent());
*/

        transactionItem.setTypeTransaction("1");
        transactionItem.setIdTransaction(courseItem.getId());
        transactionItem.setDate(new Date());
        daoTransaction.save(transactionItem);
        transactionList.add(transactionItem);

        //Agregar StudentCourse
        DAOStudent daoStudent =  DAOStudent.getInstance();
        Student student = daoStudent.get(transactionItem.getIdStudent().getId());
        List<StudentCourse> studentCourses = student.getStudentCourses();
        StudentCourse e = new StudentCourse();
        e.setCourse(courseItem);
        e.setStatus("active");
            if(studentCourses != null){

            if (studentCourses.size() > 0){
                for (int i = 0; i < studentCourses.size(); i++) {
                    studentCourses.get(i).setStatus("inactive");
                }

            }


        }else {
            studentCourses = new ArrayList<StudentCourse>();
        }
        studentCourses.add(e);
        student.setStudentCourses(studentCourses);
        daoStudent.save(student);

        //Reseteando transaccion
        transactionItem = new Transaction();
    }
}
