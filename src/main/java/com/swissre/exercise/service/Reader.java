package com.swissre.exercise.service;

import com.swissre.exercise.dto.Employee;

import java.nio.file.Path;
import java.util.Collection;

public interface Reader {
    Collection<Employee> read(Path source);

}
