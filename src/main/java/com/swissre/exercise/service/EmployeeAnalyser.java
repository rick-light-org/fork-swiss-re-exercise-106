package com.swissre.exercise.service;

import com.swissre.exercise.dto.Employee;

import java.util.Collection;

public interface EmployeeAnalyser {

    Collection<Employee> getListManagersWithSmallSalary(Collection<Employee> employees);

    Collection<Employee> getListManagersWithBigSalary(Collection<Employee> employees);

    Collection<Employee> getListEmployeesTooNested(Collection<Employee> employees);

}
