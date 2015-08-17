package com.ibs.academic.models;

import org.mongodb.morphia.annotations.Embedded;

/**
 * Created by nathaniel on 3/20/14.
 */

@Embedded
public class Schedule {

    /*LUNES
    MARTES
    MIERCOLES
    JUEVES
    VIERNES*/
    private String day;
    private float startTime;
    private float endTime;
    private float hours_reserved;

    public Schedule() {

    }

    public Schedule(String day, float startTime, float endTime, float hours_reserved) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.hours_reserved = hours_reserved;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public float getStartTime() {
        return startTime;
    }

    public void setStartTime(float startTime) {
        this.startTime = startTime;
    }

    public float getEndTime() {
        return endTime;
    }

    public void setEndTime(float endTime) {
        this.endTime = endTime;
    }

    public float getHours_reserved() {
        return hours_reserved;
    }

    public void setHours_reserved(float hours_reserved) {
        this.hours_reserved = hours_reserved;
    }
}
