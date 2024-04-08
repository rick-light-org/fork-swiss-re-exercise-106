package com.swissre.exercise.service;

import com.swissre.exercise.dto.Employee;

import java.util.Collection;

public interface TreeBuilder {
    Collection<Employee> build(Collection<Employee> employees) ;
}
