package com.swissre.exercise.service.calculator;

import com.swissre.exercise.dto.Employee;

public class AverageSalaryCalculator {
    public float getUnitAverageSalary(Employee employee) {
        if (employee.getSubordinates().isEmpty()) {
            return 0;
        }
        return employee.getSubordinates()
                .stream()
                .mapToInt(Employee::getSalary).sum() / (float) employee.getSubordinates().size();
    }
}
