package com.swissre.exercise.service.predicate;

import com.swissre.exercise.ApplicationProperties;
import com.swissre.exercise.dto.Employee;
import com.swissre.exercise.service.calculator.NestedLevelCalculator;

import java.util.function.Predicate;

public class NestedLevelPredicate implements Predicate<Employee> {
    private final ApplicationProperties properties;

    private final NestedLevelCalculator nestedLevelCalculator;

    public NestedLevelPredicate(ApplicationProperties properties, NestedLevelCalculator nestedLevelCalculator) {
        this.properties = properties;
        this.nestedLevelCalculator = nestedLevelCalculator;
    }

    @Override
    public boolean test(Employee employee) {
        return nestedLevelCalculator.getLevel(employee) > properties.getMaxTreeLevel();
    }


}
