package com.enterpriseapplications.authenticationspring.dto;


import com.enterpriseapplications.authenticationspring.entities.enums.ExternalProvider;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ExternalUserDto extends UserDto
{
    @NotNull
    @PositiveOrZero
    private Long externalID;

    @NotNull
    @NotBlank
    private ExternalProvider externalProvider;
}
