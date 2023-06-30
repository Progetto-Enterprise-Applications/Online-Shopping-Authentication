package com.enterpriseapplications.authenticationspring.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private Long externalID;

    @Column(name = "EXTERNAL_PROVIDER",unique = false,nullable = false,updatable = false)
    private Long externalProvider;
}
