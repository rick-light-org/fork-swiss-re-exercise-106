package com.swissre.exercise.service;

import com.swissre.exercise.dto.Employee;
import com.swissre.exercise.service.calculator.AverageSalaryCalculator;
import com.swissre.exercise.service.impl.EmployeeFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AverageSalaryCalculatorTest {
    AverageSalaryCalculator calculator = new AverageSalaryCalculator();

    @Test
    void getUnitAverageSalary_whenNoSubordinates_shouldReturnZero() {
        Employee manager = EmployeeFactory.builder()
                .withSalary(100)
                .build();

        assertEquals(0, calculator.getUnitAverageSalary(manager));
    }

    @Test
    void getUnitAverageSalary_whenSubordinatesAvailable_shouldReturnAverage() {
        Employee manager = EmployeeFactory.builder()
                .withSubordinate(EmployeeFactory.builder().withSalary(100).build())
                .withSubordinate(EmployeeFactory.builder().withSalary(200).build())
                .withSubordinate(EmployeeFactory.builder().withSalary(300).build())
                .build();

        assertEquals(200, calculator.getUnitAverageSalary(manager));
    }
}