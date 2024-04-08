package com.swissre.exercise.service;

import com.swissre.exercise.dto.Employee;
import com.swissre.exercise.exception.TreeRecursiveException;
import com.swissre.exercise.service.calculator.NestedLevelCalculator;
import com.swissre.exercise.service.impl.EmployeeFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NestedLevelCalculatorTest {
    NestedLevelCalculator calculator = new NestedLevelCalculator();

    @Test
    void getLevel_whenNoManager_shouldReturnZero() {
        Employee employee = EmployeeFactory.builder().build();

        assertEquals(0, calculator.getLevel(employee));
    }

    @Test
    void getLevel_whenTreeHasCycles_shouldThrowExceptions() {
        Employee employee = EmployeeFactory.builder()
                .withId(1)
                .withManagerId(2)
                .build();
        Employee manager = EmployeeFactory.builder()
                .withId(2)
                .withManagerId(1)
                .build();

        employee.setManager(manager);
        manager.setManager(employee);

        assertThrows(TreeRecursiveException.class, () -> calculator.getLevel(employee));
    }

    @Test
    void getLevel_whenNested_shouldIncrementLevel() {
        Employee subordinate = EmployeeFactory.builder()
                .withId(3)
                .build();
        EmployeeFactory.builder()
                .withId(1)
                .withSubordinate(EmployeeFactory.builder()
                        .withId(2)
                        .withSubordinate(subordinate)
                        .build())
                .build();

        assertEquals(2, calculator.getLevel(subordinate));
    }
}