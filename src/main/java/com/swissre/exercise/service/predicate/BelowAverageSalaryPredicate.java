package com.swissre.exercise.service.predicate;

import com.swissre.exercise.ApplicationProperties;
import com.swissre.exercise.dto.Employee;
import com.swissre.exercise.service.calculator.AverageSalaryCalculator;

import java.util.function.Predicate;

public class BelowAverageSalaryPredicate implements Predicate<Employee> {
    private final ApplicationProperties properties;
    private final AverageSalaryCalculator salaryCalculator;

    public BelowAverageSalaryPredicate(ApplicationProperties properties, AverageSalaryCalculator salaryCalculator) {
        this.properties = properties;
        this.salaryCalculator = salaryCalculator;
    }

    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() < properties.getBelowSalaryBoundary() * salaryCalculator.getUnitAverageSalary(employee);
    }
}
