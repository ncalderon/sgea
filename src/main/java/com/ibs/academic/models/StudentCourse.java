package com.ibs.academic.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Reference;

import java.util.List;

/**
 * Created by nathaniel on 4/10/14.
 */
@Embedded
public class StudentCourse {
    @Reference("course")
    private Course course;
    @Embedded("subject_results")
    private List<SubjectResult> subjectResults;
    private String status;

    public StudentCourse() {

    }

    public StudentCourse(Course course, List<SubjectResult> subjectResults, String status) {
        this.course = course;
        this.subjectResults = subjectResults;
        this.status = status;
    }


    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<SubjectResult> getSubjectResults() {
        return subjectResults;
    }

    public void setSubjectResults(List<SubjectResult> subjectResults) {
        this.subjectResults = subjectResults;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
