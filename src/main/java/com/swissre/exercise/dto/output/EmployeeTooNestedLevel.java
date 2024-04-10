/**
 * tst
 *
 * @author: Riccardo_Bruno
 * @project: fork-swiss-re-exercise-106
 */


package com.swissre.exercise.dto.output;

public record EmployeeTooNestedLevel(Integer id, String firstName, String lastName, int diffLevel) {

    @Override
    public String toString() {
        return "EmployeeTooNestedLevel{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", diffLevel=" + diffLevel +
                '}';
    }
}
