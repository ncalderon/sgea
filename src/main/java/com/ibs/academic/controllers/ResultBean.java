package com.ibs.academic.controllers;

import com.ibs.academic.dao.*;
import com.ibs.academic.models.*;
import org.bson.types.ObjectId;
import org.primefaces.event.SelectEvent;
import sun.rmi.runtime.Log;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nathaniel on 4/11/14.
 */

@ManagedBean(name = "resultBean")
@ViewScoped
public class ResultBean {

    private DAOStudent daoStudent;
    private SubjectCourse subjectCourse;
    private Student student;
    private List<Result> results;
    private  Result result;
    private int index;
    @ManagedProperty("#{loginBean}")
    private LoginBean login;

    @PostConstruct
    public void init(){
        index = 0;
        resetDialog();
        daoStudent = DAOStudent.getInstance();
    }

    public void resetDialog (){
        result = new Result();
        subjectCourse = new SubjectCourse();
        student = new Student();
        results = new ArrayList<Result>();
    }

    public List<SubjectCourse> findSubjectCourse(String name){
        return DAOSubjectCourse.getInstance().findByTeacher(DAOEmployee.getInstance().get(login.getCurrentUser().getEmployee().getId()), name);
    }

    public List <Student> findStudent(String name){
        return DAOStudent.getInstance().findByName(name);
    }

    public void subjectCourseSelected(SelectEvent event) {
        resetDialog();
        subjectCourse =(SubjectCourse) event.getObject();
    }

    public void studentSelected(SelectEvent event) {
        result = new Result();
        student = new Student();
        results = new ArrayList<Result>();

        student = (Student) event.getObject();
        if(subjectCourse != null){
            StudentCourse studentCourse = student.getStudentCourses().get(student.getStudentCourses().size()-1);
            List<SubjectResult>  subjectResults = studentCourse.getSubjectResults();
            if(subjectResults != null){
                SubjectResult subjectResult;
                for(int i = 0; i < subjectResults.size(); i++){
                    subjectResult = subjectResults.get(i);
                    if(subjectResult.getSubjectCourse().getId().toString().equalsIgnoreCase(subjectCourse.getId().toString())){
                        results = subjectResult.getResults();
                        index = i;
                    }
                }
            }
        }
    }

    public void newItem () {
        result = new Result();
    }
    private void reset (){


    }

    public void add (){
        result.setDate(new Date());
        results.add(result);
        SubjectResult subjectResult = new SubjectResult();
        if(student.getStudentCourses().get(student.getStudentCourses().size() - 1).getSubjectResults() == null){
            subjectResult.setSubjectCourse(subjectCourse);
            subjectResult.setResults(results);
            List <SubjectResult> subjectResults = new ArrayList<com.ibs.academic.models.SubjectResult>();
            subjectResults.add(subjectResult);
            student.getStudentCourses().get(student.getStudentCourses().size() - 1).setSubjectResults(subjectResults);
        }else if(index == 0){
            subjectResult.setSubjectCourse(subjectCourse);
            subjectResult.setResults(results);
            student.getStudentCourses().get(student.getStudentCourses().size() - 1).getSubjectResults().add(subjectResult);
        }else {
            student.getStudentCourses().get(student.getStudentCourses().size() - 1).getSubjectResults().get(index).setResults(results);
        }
        daoStudent.save(student);
        result = new Result();
        student = daoStudent.get(student.getId());
        results = student.getStudentCourses().get(student.getStudentCourses().size() - 1).getSubjectResults().get(index).getResults();
    }

    public void delete (Result resultItem){
        results.remove(resultItem);
        student.getStudentCourses().get(student.getStudentCourses().size() - 1).getSubjectResults().get(index).setResults(results);
        daoStudent.save(student);
        student = daoStudent.get(student.getId());
        results = student.getStudentCourses().get(student.getStudentCourses().size() - 1).getSubjectResults().get(index).getResults();
    }

    public SubjectCourse getSubjectCourse() {
        return subjectCourse;
    }

    public void setSubjectCourse(SubjectCourse subjectCourse) {
        this.subjectCourse = subjectCourse;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public LoginBean getLogin() {
        return login;
    }

    public void setLogin(LoginBean login) {
        this.login = login;
    }
}
