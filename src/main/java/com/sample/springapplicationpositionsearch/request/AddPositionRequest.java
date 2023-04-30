package com.sample.springapplicationpositionsearch.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AddPositionRequest {

    @NotBlank(message = "Title cannot be empty")
    @Size(min = 3, max = 50, message = "Title must be more than 2 and less than 51 characters")
    @ApiModelProperty(value = "Title of the job", example = "Software Engineer")
    private String title;
    @NotBlank(message = "Location cannot be empty")
    @Size(max = 50, message = "Location must be less than 51 characters")
    private String location;
}
