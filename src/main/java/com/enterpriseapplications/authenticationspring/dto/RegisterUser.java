package com.enterpriseapplications.authenticationspring.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterUser {

    @Email
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 20)
    private String username;

    @NotNull
    @Size(min = 5, max = 40)
    @NotBlank
    private String password;
}
