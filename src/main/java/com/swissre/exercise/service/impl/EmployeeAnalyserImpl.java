package com.swissre.exercise.service.impl;

import com.swissre.exercise.ApplicationProperties;
import com.swissre.exercise.dto.Employee;
import com.swissre.exercise.dto.output.EmployeeOutputOutstandingAverage;
import com.swissre.exercise.dto.output.EmployeeTooNestedLevel;
import com.swissre.exercise.service.EmployeeAnalyser;
import com.swissre.exercise.service.calculator.AverageSalaryCalculator;
import com.swissre.exercise.service.calculator.NestedLevelCalculator;

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

    private final AverageSalaryCalculator salaryCalculator;

    private final NestedLevelCalculator nestedLevelCalculator;

    private final ApplicationProperties properties;

    public EmployeeAnalyserImpl(Predicate<Employee> aboveAverageSalary, Predicate<Employee> belowAverageSalary, Predicate<Employee> nestedLevel,
                                AverageSalaryCalculator salaryCalculator, NestedLevelCalculator nestedLevelCalculator, ApplicationProperties properties) {
        this.aboveAverageSalary = aboveAverageSalary;
        this.belowAverageSalary = belowAverageSalary;
        this.nestedLevel = nestedLevel;
        this.salaryCalculator = salaryCalculator;
        this.nestedLevelCalculator = nestedLevelCalculator;
        this.properties = properties;
    }

    @Override
    public Collection<EmployeeOutputOutstandingAverage> getListManagersWithSmallSalary(Collection<Employee> employees) {
        return employees.stream()
                .filter(this::isManager)
                .filter(belowAverageSalary)
                .map(emp->new EmployeeOutputOutstandingAverage(emp.getId(),emp.getFirstName(),emp.getLastName(),emp.getSalary(),salaryCalculator.getUnitAverageSalary(emp),emp.getSalary()-salaryCalculator.getUnitAverageSalary(emp)))
                .toList();
    }

    @Override
    public Collection<EmployeeOutputOutstandingAverage> getListManagersWithBigSalary(Collection<Employee> employees) {
        return employees.stream()
                .filter(this::isManager)
                .filter(aboveAverageSalary)
                .map(emp->new EmployeeOutputOutstandingAverage(emp.getId(),emp.getFirstName(),emp.getLastName(),emp.getSalary(),salaryCalculator.getUnitAverageSalary(emp),emp.getSalary()-salaryCalculator.getUnitAverageSalary(emp)))
                .toList();
    }

    @Override
    public Collection<EmployeeTooNestedLevel> getListEmployeesTooNested(Collection<Employee> employees) {
        return employees.stream()
                .filter(not(this::isManager))
                .filter(nestedLevel)
                .map(emp->new EmployeeTooNestedLevel(emp.getId(),emp.getFirstName(),emp.getLastName(),nestedLevelCalculator.getLevel(emp)-properties.getMaxTreeLevel()))
                .toList();
    }

    private boolean isManager(Employee employee) {
        return !employee.getSubordinates().isEmpty();
    }
}
