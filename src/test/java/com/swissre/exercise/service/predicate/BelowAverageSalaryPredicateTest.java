package com.swissre.exercise.service.predicate;

import com.swissre.exercise.ApplicationProperties;
import com.swissre.exercise.dto.Employee;
import com.swissre.exercise.service.calculator.AverageSalaryCalculator;
import com.swissre.exercise.service.impl.ApplicationPropertiesFactory;
import com.swissre.exercise.service.impl.EmployeeFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BelowAverageSalaryPredicateTest {
    ApplicationProperties properties = ApplicationPropertiesFactory.forTests();
    BelowAverageSalaryPredicate predicate = new BelowAverageSalaryPredicate(properties, new AverageSalaryCalculator());

    @Test
    void test_whenSalaryIsBelowAverageThreshold_shouldReturnTrue() {
        Employee employee = EmployeeFactory.builder()
                .withSalary(100)
                .build();

        Employee manager = EmployeeFactory.builder()
                .withSalary(119)
                .withSubordinate(employee)
                .build();

        assertTrue(predicate.test(manager));
    }

    @Test
    void test_whenSalaryIsAboveAverageThreshold_shouldReturnFalse() {
        Employee employee = EmployeeFactory.builder()
                .withSalary(100)
                .build();

        Employee manager = EmployeeFactory.builder()
                .withSalary(125)
                .withSubordinate(employee)
                .build();

        assertFalse(predicate.test(manager));
    }
}