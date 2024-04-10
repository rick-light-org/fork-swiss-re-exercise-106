package com.swissre.exercise.service.predicate;

import com.swissre.exercise.ApplicationProperties;
import com.swissre.exercise.dto.Employee;
import com.swissre.exercise.exception.TreeRecursiveException;
import com.swissre.exercise.service.calculator.NestedLevelCalculator;
import com.swissre.exercise.service.TreeBuilder;
import com.swissre.exercise.service.impl.ApplicationPropertiesFactory;
import com.swissre.exercise.service.impl.EmployeeFactory;
import com.swissre.exercise.service.impl.TreeBuilderImpl;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NestedLevelPredicateTest {
    ApplicationProperties properties = ApplicationPropertiesFactory.forTests();

    NestedLevelCalculator nestedLevelCalculator = new NestedLevelCalculator();
    NestedLevelPredicate predicate = new NestedLevelPredicate(properties,nestedLevelCalculator);
    TreeBuilder treeBuilder = new TreeBuilderImpl();

    @Test
    void test_whenCycleFound_shouldThrowException() {
        Collection<Employee> employees = List.of(
                EmployeeFactory.builder()
                        .withId(1)
                        .withManagerId(2)
                        .build(),
                EmployeeFactory.builder()
                        .withId(2)
                        .withManagerId(1)
                        .build()
        );
        Collection<Employee> tree = treeBuilder.build(employees);

        assertThrows(TreeRecursiveException.class, () -> predicate.test(tree.iterator().next()));
    }

    @Test
    void test_whenLevelIfLessThanFour_shouldReturnFalse() {
        Employee subordinate = EmployeeFactory.builder().build();
        EmployeeFactory.builder()
                .withSubordinate(subordinate)
                .build();

        assertFalse(predicate.test(subordinate));
    }

    @Test
    void test_whenLevelIsFour_shouldReturnFalse() {
        Employee subordinate = EmployeeFactory.builder()
                .withId(5)
                .build();
        EmployeeFactory.builder()
                .withId(1)
                .withSubordinate(EmployeeFactory.builder()
                        .withId(2)
                        .withSubordinate(EmployeeFactory.builder()
                                .withId(3)
                                .withSubordinate(EmployeeFactory.builder()
                                        .withId(4)
                                        .withSubordinate(subordinate)
                                        .build())
                                .build())
                        .build())
                .build();

        assertFalse(predicate.test(subordinate));
    }

    @Test
    void test_whenLevelIsMoreThanFour_shouldReturnTrue() {
        Employee subordinate = EmployeeFactory.builder()
                .withId(6)
                .build();
        EmployeeFactory.builder()
                .withId(1)
                .withSubordinate(EmployeeFactory.builder()
                        .withId(2)
                        .withSubordinate(EmployeeFactory.builder()
                                .withId(3)
                                .withSubordinate(EmployeeFactory.builder()
                                        .withId(4)
                                        .withSubordinate(EmployeeFactory.builder()
                                                .withSubordinate(subordinate)
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build();

        assertTrue(predicate.test(subordinate));
    }
}