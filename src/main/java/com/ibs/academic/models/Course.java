package com.ibs.academic.models;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

import java.util.List;

/**
 * Created by nathaniel on 3/19/14.
 */

@Entity("course")
public class Course extends MongoObject {
    /*ToDo:
    * Crear dependencia
    * */
    private String name;
    private int numberCourse;
    private String status;
    @Reference("subject_course")
    private List<SubjectCourse> subjectsCourses;

    public Course() {

    }
    /* Int Number Course
    1 - Kinder
    2 - Preprimario
    3 - Primero de Basica
    4 - Segundo de Basica
    5 - Tercero de Basica
    6 - Cuarto de Basica
    7 - Quinto de Basica
    8 - Sexto de Basica
    9 - Septimo de Basica
    10- Octavo de Basica
    11- Primero Bachiller
    12- Segundo Bachiller
    13- Tercero Bachiller
    14- Cuarto Bachiller
     */

    public Course(String name, int numberCourse, String status, List<SubjectCourse> subjectsCourses) {
        this.name = name;
        this.numberCourse = numberCourse;
        this.status = status;
        this.subjectsCourses = subjectsCourses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberCourse() {
        return numberCourse;
    }

    public void setNumberCourse(int numberCourse) {
        this.numberCourse = numberCourse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SubjectCourse> getSubjectsCourses() {
        return subjectsCourses;
    }

    public void setSubjectsCourses(List<SubjectCourse> subjectsCourses) {
        this.subjectsCourses = subjectsCourses;
    }
}
