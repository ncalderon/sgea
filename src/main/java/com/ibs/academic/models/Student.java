package com.ibs.academic.models;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

import java.util.Date;
import java.util.List;

/**
 * Created by nathaniel on 3/19/14.
 */
@Entity("student")
public class Student extends Person {

    private Date dateStarted;
    private Date dateEnd;
    @Reference("tutor")
    private Tutor tutor;
    /*
        null = no inscrito
        0 = no inscrito
        1 = inscrito
        2 = promovido (reinscripcion)
     */
    private String academicStatus;
    @Embedded("address")
    private List<Address> address;
    @Embedded("student_course")
    private List<StudentCourse> studentCourses;

    public Student() {

    }

    public Student(Date dateStarted, Date dateEnd, Tutor tutor, String academicStatus, List<Address> address, List<StudentCourse> studentCourses) {
        this.dateStarted = dateStarted;
        this.dateEnd = dateEnd;
        this.tutor = tutor;
        this.academicStatus = academicStatus;
        this.address = address;
        this.studentCourses = studentCourses;
    }

    public Student(String firstname, String lastname, String identification, String typeIdentification, String status, List<Phone> phones, Date dateStarted, Date dateEnd, Tutor tutor, String academicStatus, List<Address> address, List<StudentCourse> studentCourses) {
        super(firstname, lastname, identification, typeIdentification, status, phones);
        this.dateStarted = dateStarted;
        this.dateEnd = dateEnd;
        this.tutor = tutor;
        this.academicStatus = academicStatus;
        this.address = address;
        this.studentCourses = studentCourses;
    }

    public Date getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public String getAcademicStatus() {
        return academicStatus;
    }

    public void setAcademicStatus(String academicStatus) {
        this.academicStatus = academicStatus;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public List<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(List<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }
}