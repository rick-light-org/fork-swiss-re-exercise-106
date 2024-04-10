package com.swissre.exercise.service;

import com.swissre.exercise.dto.Employee;
import com.swissre.exercise.dto.output.EmployeeOutputOutstandingAverage;
import com.swissre.exercise.dto.output.EmployeeTooNestedLevel;

import java.util.Collection;

public interface Printer {

    void print(Collection<EmployeeOutputOutstandingAverage>managersWithSmallSalary,
               Collection<EmployeeOutputOutstandingAverage>managersWithBigSalary,
               Collection<EmployeeTooNestedLevel>tooNestedEmployees);

}
