package com.enterpriseapplications.authenticationspring.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUser {

    @Email
    @NotBlank
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
