package com.swissre.exercise;

import com.swissre.exercise.dto.Employee;
import com.swissre.exercise.dto.output.EmployeeOutputOutstandingAverage;
import com.swissre.exercise.dto.output.EmployeeTooNestedLevel;
import com.swissre.exercise.service.*;
import com.swissre.exercise.service.calculator.AverageSalaryCalculator;
import com.swissre.exercise.service.calculator.NestedLevelCalculator;
import com.swissre.exercise.service.impl.ConsolePrinter;
import com.swissre.exercise.service.impl.CsvConverter;
import com.swissre.exercise.service.impl.CsvReader;
import com.swissre.exercise.service.impl.EmployeeAnalyserImpl;
import com.swissre.exercise.service.impl.TreeBuilderImpl;
import com.swissre.exercise.service.predicate.AboveAverageSalaryPredicate;
import com.swissre.exercise.service.predicate.BelowAverageSalaryPredicate;
import com.swissre.exercise.service.predicate.NestedLevelPredicate;

import java.nio.file.Path;
import java.util.Collection;

public class Application {
    private final Reader reader;
    private final TreeBuilder treeBuilder;
    private final Printer printer;

    private final EmployeeAnalyser employeeAnalyser;

    public Application(Reader reader, TreeBuilder treeBuilder, Printer printer, EmployeeAnalyser employeeAnalyser) {
        this.reader = reader;
        this.treeBuilder = treeBuilder;
        this.printer = printer;
        this.employeeAnalyser = employeeAnalyser;
    }

    public void run(Path sourceFile) {
        //Read CSV file and insert all records in a Map
        Collection<Employee> employees = reader.read(sourceFile);

        // build the hierarchy
        Collection<Employee> employeesTree = treeBuilder.build(employees);

        //calculate too nested employees(In this phase I check also if there are cyclic redundancies in the input data)
        Collection<EmployeeTooNestedLevel> tooNestedEmployees = employeeAnalyser.getListEmployeesTooNested(employeesTree);

        //calculate managers with smaller salary
        Collection<EmployeeOutputOutstandingAverage> managersWithSmallSalary = employeeAnalyser.getListManagersWithSmallSalary(employeesTree);

        //calculate managers with bigger salary
        Collection<EmployeeOutputOutstandingAverage> managersWithBigSalary = employeeAnalyser.getListManagersWithBigSalary(employeesTree);

        //print results
        printer.print(managersWithSmallSalary, managersWithBigSalary, tooNestedEmployees);
    }

    public static void main(String[] args) {

        ApplicationProperties properties = new ApplicationProperties(Path.of("src/main/resources/application.properties"));
        CsvReader csvReader = new CsvReader(new CsvConverter(), properties);
        AverageSalaryCalculator averageSalaryCalculator = new AverageSalaryCalculator();
        NestedLevelCalculator nestedLevelCalculator = new NestedLevelCalculator();

        EmployeeAnalyser analyser = new EmployeeAnalyserImpl(
                new AboveAverageSalaryPredicate(properties, averageSalaryCalculator),
                new BelowAverageSalaryPredicate(properties, averageSalaryCalculator),
                new NestedLevelPredicate(properties, nestedLevelCalculator),
                new AverageSalaryCalculator(),
                new NestedLevelCalculator(),
                properties

        );

        ConsolePrinter printer = new ConsolePrinter();
        Application app = new Application(csvReader, new TreeBuilderImpl(), printer, analyser);
        app.run(Path.of("src/main/resources/swiss-re-20.csv"));
    }

}