package com.swissre.exercise.service.predicate;

import com.swissre.exercise.ApplicationProperties;
import com.swissre.exercise.dto.Employee;
import com.swissre.exercise.service.calculator.AverageSalaryCalculator;
import com.swissre.exercise.service.impl.ApplicationPropertiesFactory;
import com.swissre.exercise.service.impl.EmployeeFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AboveAverageSalaryPredicateTest {
    ApplicationProperties properties = ApplicationPropertiesFactory.forTests();
    AboveAverageSalaryPredicate predicate = new AboveAverageSalaryPredicate(properties, new AverageSalaryCalculator());

    @Test
    void test_whenSalaryIsAboveAverageThreshold_shouldReturnTrue() {
        Employee employee = EmployeeFactory.builder()
                .withSalary(100)
                .build();

        Employee manager = EmployeeFactory.builder()
                .withSalary(151)
                .withSubordinate(employee)
                .build();

        assertTrue(predicate.test(manager));
    }

    @Test
    void test_whenSalaryIsBelowAverageThreshold_shouldReturnFalse() {
        Employee employee = EmployeeFactory.builder()
                .withSalary(100)
                .build();

        Employee manager = EmployeeFactory.builder()
                .withSalary(149)
                .withSubordinate(employee)
                .build();

        assertFalse(predicate.test(manager));
    }
}