/**
 * tst
 *
 * @author: Riccardo_Bruno
 * @project: swiss-re-exercise-106
 */


package com.swissre.exercise.service.impl;

import com.swissre.exercise.ApplicationProperties;
import com.swissre.exercise.dto.Employee;
import com.swissre.exercise.service.EmployeeConverter;
import com.swissre.exercise.service.Reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

/**
 * this class  is used to operate on the CSV file
 */
public class CsvReader implements Reader {
    private final EmployeeConverter converter;
    private final ApplicationProperties properties;

    public CsvReader(EmployeeConverter converter, ApplicationProperties properties) {
        this.converter = converter;
        this.properties = properties;
    }

    @Override
    public Collection<Employee> read(Path source) {
        try {
            return Files.lines(source)
                    .skip(properties.getNumberOfHeadersToSkip())
                    .map(converter::convert)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Error reading file", e);
        }
    }
}
