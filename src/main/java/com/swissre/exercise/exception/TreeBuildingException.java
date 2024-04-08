package com.swissre.exercise.exception;

/**
 * Exception in case we have issues building the Tree
 * specifically in the case we cannot find a relative manager indicated in the record employee
 */
public class TreeBuildingException extends RuntimeException {
    public TreeBuildingException(String message) {
        super(message);
    }
}