package com.ibs.academic.models;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

/**
 * Created by nathaniel on 3/19/14.
 */

@Entity("user")
public class User extends MongoObject{
    private String username;
    private String password;
    @Reference("role")
    private Role role;
    @Reference("student")
    private Student student;
    @Reference("employee")
    private Employee employee;
    private String status;

    public User() {

    }

    public User(String username, String password, Role role, String status, Student student, Employee employee) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
        this.student = student;
        this.employee = employee;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
