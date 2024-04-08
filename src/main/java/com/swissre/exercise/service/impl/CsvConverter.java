package com.swissre.exercise.service.impl;

import com.swissre.exercise.dto.Employee;
import com.swissre.exercise.service.EmployeeConverter;

/**
 *this class  make the conversion between the specific CSV line to the DTO
 */
public class CsvConverter implements EmployeeConverter {
    public static final String COMMA_DELIMITER = ",";

    /**
     * Convert a string line into an {@link Employee}  DTO
     * @param str
     * @return {@link Employee}  DTO
     */
    @Override
    public Employee convert(String str) {
        String[] arr= str.split(COMMA_DELIMITER);
        return new Employee(
                Integer.parseInt(arr[0]),
                arr[1],
                arr[2],
                Integer.parseInt(arr[3]),
                arr.length==5 ? Integer.parseInt(arr[4]) : null
        );
    }
}
