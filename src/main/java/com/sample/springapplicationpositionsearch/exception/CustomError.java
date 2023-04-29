package com.sample.springapplicationpositionsearch.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class CustomError {
    private final String problem;
    private final Map<String, String> fields;

    public CustomError(Map<String, String> fields) {
        this.problem = "Issues with the following: ";
        this.fields = fields;
    }
}

