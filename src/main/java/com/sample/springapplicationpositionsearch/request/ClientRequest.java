package com.sample.springapplicationpositionsearch.request;

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
    private String name;
    @NotBlank(message = "Name cannot be empty")
    @Pattern(regexp = REGEX, message = "Invalid email format")
    private String email;
}
