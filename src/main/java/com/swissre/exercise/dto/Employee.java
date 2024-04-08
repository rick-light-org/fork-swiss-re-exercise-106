package com.swissre.exercise.dto;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Class tha represents the Node of the tree
 */
public class Employee{

    private final Integer id ;
    private final  String firstName ;
    private final  String lastName ;
    private Employee manager ;
    private final  Integer salary ;
    private final  Integer managerId ;

    private Collection<Employee> subordinates = new ArrayList<>();

    public Employee(Integer id, String firstName, String lastName, Integer salary, Integer managerId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.managerId = managerId;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Integer getSalary() {
        return salary;
    }


    public Integer getManagerId() {
        return managerId;
    }


    public Collection<Employee> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(Collection<Employee> subordinates) {
        this.subordinates = subordinates;
    }

    @Override
    public String toString() {
        return "Employee:[" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ']';
    }
}
