package com.swissre.exercise.service.impl;

import com.swissre.exercise.dto.Employee;
import com.swissre.exercise.service.EmployeeAnalyser;

import java.util.Collection;
import java.util.function.Predicate;

import static java.util.function.Predicate.not;

/**
 * This class provide all the necessary methods  to make operation on the tree
 */
public class EmployeeAnalyserImpl implements EmployeeAnalyser {
    private final Predicate<Employee> aboveAverageSalary;
    private final Predicate<Employee> belowAverageSalary;
    private final Predicate<Employee> nestedLevel;

    public EmployeeAnalyserImpl(Predicate<Employee> aboveAverageSalary, Predicate<Employee> belowAverageSalary, Predicate<Employee> nestedLevel) {
        this.aboveAverageSalary = aboveAverageSalary;
        this.belowAverageSalary = belowAverageSalary;
        this.nestedLevel = nestedLevel;
    }

    @Override
    public Collection<Employee> getListManagersWithSmallSalary(Collection<Employee> employees) {
        return employees.stream()
                .filter(this::isManager)
                .filter(belowAverageSalary)
                .toList();
    }

    @Override
    public Collection<Employee> getListManagersWithBigSalary(Collection<Employee> employees) {
        return employees.stream()
                .filter(this::isManager)
                .filter(aboveAverageSalary)
                .toList();
    }

    @Override
    public Collection<Employee> getListEmployeesTooNested(Collection<Employee> employees) {
        return employees.stream()
                .filter(not(this::isManager))
                .filter(nestedLevel)
                .toList();
    }

    private boolean isManager(Employee employee) {
        return !employee.getSubordinates().isEmpty();
    }
}
