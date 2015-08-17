package com.ibs.academic.controllers;

import com.ibs.academic.dao.DAOCourse;
import com.ibs.academic.dao.DAOStudent;
import com.ibs.academic.dao.DAOTransaction;
import com.ibs.academic.models.Course;
import com.ibs.academic.models.Student;
import com.ibs.academic.models.Transaction;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Angel Rene Garcia on 04/10/14.
 */
@ManagedBean(name="assistanceBean")
@ViewScoped
public class AssistanceBean implements Serializable {
    private Transaction transactionItem;
    private DAOTransaction daoTransaction;
    private List<Transaction> transactionList;
    private Course courseItem;
    private ObjectId courseId;

    @PostConstruct
    public void init(){
        setTransactionItem(new Transaction());
        daoTransaction = DAOTransaction.getInstance();
        courseItem = new Course();
        //setTransactionList(daoTransaction.getTransactionsByType("5"));
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
    public void studentSelected(SelectEvent event) {
        Student student = (Student)event.getObject();
        transactionItem.setIdStudent(student);

    }

    public List<Course> findCourse(String name){
        DAOCourse daoCourse = DAOCourse.getInstance();
        Query<Course> query = daoCourse.getDs().createQuery(Course.class);
        query.or(
                query.criteria("name").equal(Pattern.compile(name, Pattern.CASE_INSENSITIVE))
        );
        return query.asList();
    }
    public void courseSelected(SelectEvent event) {
        Course course = (Course)event.getObject();
       //transactionItem.

    }
    public void add(){
        transactionItem.setTypeTransaction("3");
        transactionItem.setDate(new Date());
        //daoTransaction.save(transactionItem);
        //transactionList.add(transactionItem);
        transactionItem = new Transaction();
    }


    public Transaction getTransactionItem() {
        return transactionItem;
    }

    public void setTransactionItem(Transaction transactionItem) {
        this.transactionItem = transactionItem;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public ObjectId getCourseId() {
        return courseId;
    }

    public void setCourseId(ObjectId courseId) {
        this.courseId = courseId;
    }

    public Course getCourseItem() {
        return courseItem;
    }

    public void setCourseItem(Course courseItem) {
        this.courseItem = courseItem;
    }
}
