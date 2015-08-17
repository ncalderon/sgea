package com.ibs.academic.models;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

import java.util.List;

/**
 * Created by nathaniel on 4/10/14.
 */

@Embedded
public class SubjectResult {
    @Reference("subjectCourse")
    private SubjectCourse subjectCourse;
    @Embedded("results")
    private List<Result> results;

    public SubjectCourse getSubjectCourse() {
        return subjectCourse;
    }

    public void setSubjectCourse(SubjectCourse subjectCourse) {
        this.subjectCourse = subjectCourse;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
