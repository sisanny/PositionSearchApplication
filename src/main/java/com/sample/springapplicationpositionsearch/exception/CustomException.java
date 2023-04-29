package com.sample.springapplicationpositionsearch.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException {
    private final FieldName field;

    public CustomException(String message, FieldName field) {
        super(message);
        this.field = field;
    }
}
