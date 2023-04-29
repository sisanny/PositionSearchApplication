package com.sample.springapplicationpositionsearch.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SearchPositionRequest extends PositionRequest {

    @NotBlank(message = "Keyword cannot be empty")
    @Size(min = 3, max = 50, message = "Keyword must be more than 2 and less than 51 characters")
    private String keyword;
}
