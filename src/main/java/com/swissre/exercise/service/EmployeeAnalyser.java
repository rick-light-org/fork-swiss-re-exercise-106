package com.swissre.exercise.service;

import com.swissre.exercise.dto.Employee;
import com.swissre.exercise.dto.output.EmployeeOutputOutstandingAverage;
import com.swissre.exercise.dto.output.EmployeeTooNestedLevel;

import java.util.Collection;

public interface EmployeeAnalyser {

    Collection<EmployeeOutputOutstandingAverage> getListManagersWithSmallSalary(Collection<Employee> employees);

    Collection<EmployeeOutputOutstandingAverage> getListManagersWithBigSalary(Collection<Employee> employees);

    Collection<EmployeeTooNestedLevel> getListEmployeesTooNested(Collection<Employee> employees);

}
