package com.ibs.academic.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

import java.util.List;

/**
 * Created by nathaniel on 4/9/14.
 */

// Prueba
@Entity("subject_course")
public class SubjectCourse extends MongoObject{

    private ObjectId idCourse;
    @Reference("subject")
    private Subject subject;
    @Reference("teacher")
    private Employee teacher;
    private float hours;
    @Embedded("schedule")
    private List<Schedule> schedule;

    public SubjectCourse() {

    }

    public SubjectCourse(ObjectId idCourse, Subject subject, Employee teacher, float hours, List<Schedule> schedule) {
        this.idCourse = idCourse;
        this.subject = subject;
        this.teacher = teacher;
        this.hours = hours;
        this.schedule = schedule;
    }

    public ObjectId getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(ObjectId idCourse) {
        this.idCourse = idCourse;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Employee getTeacher() {
        return teacher;
    }

    public void setTeacher(Employee teacher) {
        this.teacher = teacher;
    }

    public float getHours() {
        return hours;
    }

    public void setHours(float hours) {
        this.hours = hours;
    }

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }
}
