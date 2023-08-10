package com.enterpriseapplications.authenticationspring.entities;


import com.enterpriseapplications.authenticationspring.entities.enums.ExternalProvider;
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
public class LocalUser extends User
{
    @Column(name = "PASSWORD",unique = false,nullable = false)
    private String password;
}
