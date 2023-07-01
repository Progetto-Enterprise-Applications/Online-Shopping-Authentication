package com.enterpriseapplications.authenticationspring.entities;


import com.enterpriseapplications.authenticationspring.entities.enums.ExternalProvider;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalUser extends User
{
    @Column(name = "EXTERNAL_ID",unique = true,nullable = false,updatable = false)
    private String externalID;

    @Enumerated(EnumType.STRING)
    @Column(name = "EXTERNAL_PROVIDER",nullable = false,updatable = false)
    private ExternalProvider externalProvider;
}
