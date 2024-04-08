package com.swissre.exercise.exception;

/**
 * Exception in case we have issues building the Tree
 * specifically in the case we get some recursive cycle in the tree
 */
public class TreeRecursiveException extends RuntimeException {
    public TreeRecursiveException(String message) {
        super(message);
    }
}