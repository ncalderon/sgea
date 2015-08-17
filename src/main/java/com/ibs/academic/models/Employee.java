package com.ibs.academic.models;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

import java.util.List;

/**
 * Created by nathaniel on 3/19/14.
 */

@Entity("employee")
public class Employee extends Person {
    // tipos: teacher, others
    @Property("type_employee")
    String typeEmployee;
    @Embedded("schedule_available")
    private List<Schedule> scheduleAvailable;

    public Employee() {
        super();
    }

    public Employee(String firstname, String lastname, String identification, String typeIdentification, String status, List<Phone> phones, String typeEmployee, List<Schedule> scheduleAvailable) {
        super(firstname, lastname, identification, typeIdentification, status, phones);
        this.scheduleAvailable = scheduleAvailable;
    }

    public List<Schedule> getScheduleAvailable() {
        return scheduleAvailable;
    }

    public void setScheduleAvailable(List<Schedule> scheduleAvailable) {
        this.scheduleAvailable = scheduleAvailable;
    }

    public String getTypeEmployee() {
        return typeEmployee;
    }

    public void setTypeEmployee(String typeEmployee) {
        this.typeEmployee = typeEmployee;
    }
}
