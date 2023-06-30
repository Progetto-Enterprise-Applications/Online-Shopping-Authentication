package com.enterpriseapplications.authenticationspring.entities;


import com.enterpriseapplications.authenticationspring.entities.enums.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(value = AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public class User
{
    @Id
    @Column(name = "ID",unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EMAIL",unique = true,nullable = false)
    @Email
    private String email;

    @Column(name = "ROLES",unique = false,nullable = false)
    private String roles;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "TYPE",unique = false,nullable = false)
    private UserType userType;

    @Column(name = "ENABLED",unique = false,nullable = false)
    @ColumnDefault("true")
    private Boolean enabled;

    @Column(name = "NOT_LOCKED",unique = false,nullable = false)
    @ColumnDefault("false")
    private Boolean notLocked;

    @CreatedDate
    @Column(name = "CREATED_DATE",unique = false,updatable = false)
    private LocalDate createdDate;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE",unique = false,updatable = true)
    private LocalDate lastModifiedDate;
}
