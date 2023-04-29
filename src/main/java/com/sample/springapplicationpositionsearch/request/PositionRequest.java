package com.sample.springapplicationpositionsearch.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PositionRequest {

    @NotBlank(message = "Location cannot be empty")
    @Size(max = 50, message = "Location must be less than 51 characters")
    private String location;
}
