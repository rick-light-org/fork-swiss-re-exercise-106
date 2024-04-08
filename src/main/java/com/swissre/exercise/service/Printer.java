package com.swissre.exercise.service;

import com.swissre.exercise.dto.Employee;

import java.util.Collection;

public interface Printer {

    void print(Collection<Employee>managersWithSmallSalary,
               Collection<Employee>managersWithBigSalary,
               Collection<Employee>tooNestedEmployees);

}
