package com.swissre.exercise.service.impl;

import com.swissre.exercise.dto.Employee;

import java.util.ArrayList;
import java.util.Collection;

public class EmployeeFactory {
    public static EmployeeBuilder builder() {
        return new EmployeeBuilder();
    }

    public static class EmployeeBuilder {
        private int id;
        private String firstName;
        private String lastName;
        private int managerId;
        private int salary;
        private Collection<Employee> subordinates = new ArrayList<>();

        public EmployeeBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public EmployeeBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public EmployeeBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public EmployeeBuilder withManagerId(int managerId) {
            this.managerId = managerId;
            return this;
        }

        public EmployeeBuilder withSalary(int salary) {
            this.salary = salary;
            return this;
        }

        public EmployeeBuilder withSubordinate(Employee subordinate) {
            this.subordinates.add(subordinate);
            return this;
        }

        public Employee build() {
            Employee manager = new Employee(id, firstName, lastName, salary, managerId);
            manager.setSubordinates(subordinates);
            subordinates.forEach(subordinate -> subordinate.setManager(manager));
            return manager;
        }
    }
}
