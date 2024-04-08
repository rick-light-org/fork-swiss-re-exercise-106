/**
 * tst
 *
 * @author: Riccardo_Bruno
 * @project: swiss-re-exercise-106
 */


package com.swissre.exercise.service.impl;

import com.swissre.exercise.dto.Employee;
import com.swissre.exercise.exception.TreeBuildingException;
import com.swissre.exercise.service.TreeBuilder;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * this class  is used to crete the tree
 */
public class TreeBuilderImpl implements TreeBuilder {
    @Override
    public Collection<Employee> build(Collection<Employee> employees) {
        Map<Integer, Employee> employeeMap = employees
                .stream()
                .collect(Collectors.toMap(Employee::getId, employee -> employee));

        for (Employee employee : employeeMap.values()) {
            if (employee.getManagerId() != null && employee.getManagerId() != 0) {
                if (employeeMap.get(employee.getManagerId()) == null) {
                    throw new TreeBuildingException("The data inserted are not consistent, the manager Id: " + employee.getManagerId() + " for the employee id: " + employee.getId() + " is not present in the CSV file");
                }
                employee.setManager(employeeMap.get(employee.getManagerId()));
                employee.getManager().getSubordinates().add(employee);
            }
        }
        return employees;
    }
}
