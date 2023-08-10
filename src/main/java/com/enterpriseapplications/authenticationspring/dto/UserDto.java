package com.enterpriseapplications.authenticationspring.dto;

import com.enterpriseapplications.authenticationspring.entities.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto
{
    @NotNull
    private Long id;

    @NotNull
    @NotBlank
    private String username;


    @NotNull
    @NotBlank
    @Email
    private String email;

    @NotNull
    @NotBlank
    private String roles;

    @NotNull
    @NotBlank
    private UserType userType;

    @NotNull
    private Boolean enabled;

    @NotNull
    private Boolean notLocked;
}
