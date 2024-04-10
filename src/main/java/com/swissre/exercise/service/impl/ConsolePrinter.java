/**
 * @author: Riccardo_Bruno
 * @project: swiss-re-exercise-106
 */


package com.swissre.exercise.service.impl;

import com.swissre.exercise.ApplicationProperties;
import com.swissre.exercise.dto.Employee;
import com.swissre.exercise.dto.output.EmployeeOutputOutstandingAverage;
import com.swissre.exercise.dto.output.EmployeeTooNestedLevel;
import com.swissre.exercise.service.calculator.AverageSalaryCalculator;
import com.swissre.exercise.service.calculator.NestedLevelCalculator;
import com.swissre.exercise.service.Printer;

import java.util.Collection;

public class ConsolePrinter implements Printer {


    @Override
    public void print(Collection<EmployeeOutputOutstandingAverage> managersWithSmallSalary,
                      Collection<EmployeeOutputOutstandingAverage> managersWithBigSalary,
                      Collection<EmployeeTooNestedLevel> tooNestedEmployees) {

        System.out.println("START MANAGERS WITH SALARY BELOW THE AVERAGE");
        managersWithSmallSalary.stream()
                .forEach(System.out::println);
        System.out.println("END MANAGERS WITH SALARY BELOW THE AVERAGE");

        System.out.println("START MANAGERS WITH SALARY ABOVE THE AVERAGE");
        managersWithBigSalary.stream()
                .forEach(System.out::println);
        System.out.println("END MANAGERS WITH SALARY ABOVE THE AVERAGE");

        System.out.println("START EMPLOYEES WITH REPORTING LINE TOO LONG");
        tooNestedEmployees.stream()
                .forEach(System.out::println);
        System.out.println("END EMPLOYEES WITH REPORTING LINE TOO LONG");

    }

}
