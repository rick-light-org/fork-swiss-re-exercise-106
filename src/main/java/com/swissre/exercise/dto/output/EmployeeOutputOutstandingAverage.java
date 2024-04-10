/**
 * tst
 *
 * @author: Riccardo_Bruno
 * @project: fork-swiss-re-exercise-106
 */


package com.swissre.exercise.dto.output;

public record EmployeeOutputOutstandingAverage(Integer id, String firstName, String lastName,Integer salary,Float average, Float diffSalary) {
    @Override
    public String toString() {
        return "EmployeeOutputOutstandingAverage{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", average=" + average +
                ", diffSalary=" + diffSalary +
                '}';
    }
}
