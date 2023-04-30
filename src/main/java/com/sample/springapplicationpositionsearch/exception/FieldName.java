package com.sample.springapplicationpositionsearch.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FieldName {
    EMAIL("email"),
    ID("id"),
    API_KEY("apiKey");

    private final String value;
}
