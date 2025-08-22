package com.example.project.exception;

public class UnknownStatusException extends RuntimeException{
    public UnknownStatusException(String message) {
        super(message);
    }
}
