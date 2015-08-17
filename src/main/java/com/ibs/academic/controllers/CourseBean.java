package com.ibs.academic.controllers;

import com.ibs.academic.dao.*;
import com.ibs.academic.models.*;
import org.mongodb.morphia.query.Query;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Josermando on 03/29/14.
 */
@ManagedBean(name="courseBean")
@ViewScoped

public class CourseBean implements  Serializable{
    private List<Course> courseList;
    private Course courseItem;
    private DAOCourse daoCourse;
    private DAOSubjectCourse daoSubjectCourse;

    // Logica SubjectCourse
    private List<SubjectCourse> subjectCourses;
    private SubjectCourse subjectCourse;

    @PostConstruct
    public void init(){
        resetDialog();
        daoCourse = DAOCourse.getInstance();
        courseList = daoCourse.find().asList();
        daoSubjectCourse = DAOSubjectCourse.getInstance();
    }

    public void resetDialog (){
        courseItem = new Course();
        subjectCourse = new SubjectCourse();
        subjectCourses = new ArrayList<SubjectCourse>();
        subjectCourse.setSubject(new Subject());
        subjectCourse.setTeacher(new Employee());
    }

    public void generateSchedules (){
        daoSubjectCourse.generateSchedules();
    }

    public void courseNewItem (){
        resetDialog();
    }

    public void newSubjectCourse (){

        subjectCourse = new SubjectCourse();
        subjectCourse.setSubject(new Subject());
        subjectCourse.setTeacher(new Employee());
    }

    public List<Employee> findEmployee(String name){
        DAOEmployee daoEmployee = DAOEmployee.getInstance();
        return daoEmployee.findTeacherByName(name);
    }

    public void teacherSelected(SelectEvent event) {
        subjectCourse.setTeacher((Employee) event.getObject());

    }

    public List<Subject> findSubject (String name){
        DAOSubject daoSubject = DAOSubject.getInstance();
        return daoSubject.findSubjectByName(name);
    }

    public void subjectSelected(SelectEvent event) {
        subjectCourse.setSubject((Subject) event.getObject());
    }

    public void addSubjectCourse () {
        subjectCourses.add(subjectCourse);
        subjectCourse = new SubjectCourse();
    }

    public void deleteSubjectCourse (SubjectCourse item) {
        subjectCourses.remove(item);
    }

    public void add(){
        for(SubjectCourse pos: subjectCourses){
            daoSubjectCourse.save(pos);
        }
        courseItem.setSubjectsCourses(subjectCourses);
        daoCourse.save(courseItem);
        courseList = daoCourse.find().asList();
        resetDialog();

    }

    public void delete(Course courseItem){
        for(SubjectCourse pos: subjectCourses){
            daoSubjectCourse.delete(pos);
        }
        daoCourse.delete(courseItem);

        courseList = daoCourse.find().asList();
        resetDialog();
    }

    public void editItem(Course courseItem){
        resetDialog();
        this.courseItem = courseItem;
        this.subjectCourses = courseItem.getSubjectsCourses();
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public Course getCourseItem() {
        return courseItem;
    }

    public void setCourseItem(Course courseItem) {
        this.courseItem = courseItem;
    }

    public List<SubjectCourse> getSubjectCourses() {
        return subjectCourses;
    }

    public void setSubjectCourses(List<SubjectCourse> subjectCourses) {
        this.subjectCourses = subjectCourses;
    }

    public SubjectCourse getSubjectCourse() {
        return subjectCourse;
    }

    public void setSubjectCourse(SubjectCourse subjectCourse) {
        this.subjectCourse = subjectCourse;
    }

}
