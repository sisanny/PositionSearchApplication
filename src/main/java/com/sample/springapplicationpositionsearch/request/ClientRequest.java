package com.sample.springapplicationpositionsearch.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {
    private static final String REGEX = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

    @NotBlank(message = "Name cannot be empty")
    @Size(max = 100, message = "Name must be less than 51 characters")
    @ApiModelProperty(value = "Name of the client", required = true, example = "John Doe")
    private String name;
    @NotBlank(message = "Name cannot be empty")
    @Pattern(regexp = REGEX, message = "Invalid email format")
    @ApiModelProperty(value = "Email of the client", required = true, example = "john.doe@example.com")
    private String email;
}
