package com.swissre.exercise.service.impl;

import com.swissre.exercise.dto.Employee;
import com.swissre.exercise.service.TreeBuilder;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TreeBuilderImplTest {
    final TreeBuilder builder = new TreeBuilderImpl();

    @Test
    void build_shouldBuildTheTree() {
        Employee ceo = EmployeeFactory.builder()
                .withId(1)
                .withFirstName("CEO")
                .build();
        Employee manager = EmployeeFactory.builder()
                .withId(2)
                .withManagerId(1)
                .withFirstName("Manager 1")
                .build();
        Employee employee = EmployeeFactory.builder()
                .withId(3)
                .withManagerId(2)
                .withFirstName("Employee")
                .build();
        Collection<Employee> flatEmployees = List.of(
                ceo,
                manager,
                employee
        );
        Collection<Employee> tree = builder.build(flatEmployees);

        assertEquals(3, tree.size());

        Employee middleManager = tree.stream()
                .filter(e -> e.getId() == 2)
                .findFirst()
                .orElseThrow();

        assertEquals(1, middleManager.getSubordinates().size());
        assertEquals(1, middleManager.getManager().getId());
    }
}