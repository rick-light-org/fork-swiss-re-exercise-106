/**
 * @author: Riccardo_Bruno
 * @project: swiss-re-exercise-106
 */


package com.swissre.exercise.service.impl;

import com.swissre.exercise.ApplicationProperties;
import com.swissre.exercise.dto.Employee;
import com.swissre.exercise.service.calculator.AverageSalaryCalculator;
import com.swissre.exercise.service.calculator.NestedLevelCalculator;
import com.swissre.exercise.service.Printer;

import java.util.Collection;

public class ConsolePrinter implements Printer {
    private final AverageSalaryCalculator salaryCalculator;

    private final NestedLevelCalculator nestedLevelCalculator;

    private final ApplicationProperties properties;

    public ConsolePrinter(AverageSalaryCalculator salaryCalculator, NestedLevelCalculator nestedLevelCalculator,ApplicationProperties properties) {
        this.salaryCalculator = salaryCalculator;
        this.nestedLevelCalculator = nestedLevelCalculator;
        this.properties = properties;
    }

    @Override
    public void print(Collection<Employee> managersWithSmallSalary,
                      Collection<Employee> managersWithBigSalary,
                      Collection<Employee> tooNestedEmployees) {

        System.out.println("START MANAGERS WITH SALARY BELOW THE AVERAGE");
        managersWithSmallSalary.stream()
                .map(e -> {
                       float averageSalary = salaryCalculator.getUnitAverageSalary(e);
                       return String.format(
                        "id = %s, salary = %s, average = %s, diff below = %s",
                        e.getId(),
                        e.getSalary(),
                        averageSalary,
                        e.getSalary()-averageSalary);
                        }
                )
                .forEach(System.out::println);
        System.out.println("END MANAGERS WITH SALARY BELOW THE AVERAGE");

        System.out.println("START MANAGERS WITH SALARY ABOVE THE AVERAGE");
        managersWithBigSalary.stream()
                .map(e -> {
                            float averageSalary = salaryCalculator.getUnitAverageSalary(e);
                            return String.format(
                                    "id = %s, salary = %s, average = %s, diff up = %s",
                                    e.getId(),
                                    e.getSalary(),
                                    averageSalary,
                                    e.getSalary()-averageSalary);
                        }
                    )
                .forEach(System.out::println);
        System.out.println("END MANAGERS WITH SALARY ABOVE THE AVERAGE");

        System.out.println("START EMPLOYEES WITH REPORTING LINE TOO LONG");
        tooNestedEmployees.stream().map(e -> String.format(
                "id = %s, diff level = %s",
                e.getId(),
                nestedLevelCalculator.getLevel(e)-properties.getMaxTreeLevel()
        ))
                .forEach(System.out::println);
        System.out.println("END EMPLOYEES WITH REPORTING LINE TOO LONG");

    }

}
