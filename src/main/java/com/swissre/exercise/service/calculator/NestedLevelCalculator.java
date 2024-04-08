package com.swissre.exercise.service.calculator;

import com.swissre.exercise.dto.Employee;
import com.swissre.exercise.exception.TreeRecursiveException;

import java.util.Collection;
import java.util.HashSet;

public class NestedLevelCalculator {

    public int getLevel(Employee employee) {
        int level = 0;
        Collection<Integer> visited = new HashSet<>();
        Employee current = employee;
        while (current.getManager() != null) {
            if (visited.contains(current.getId())) {
                throw new TreeRecursiveException("the data present a cycle, here is one of the involved employee id: " + current.getId());
            }
            visited.add(current.getId());
            current = current.getManager();
            level++;
        }
        return level;
    }


}
