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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idGenerator")
    private Long id;

    @Column(name = "EMAIL",unique = true,nullable = false)
    @Email
    private String email;

    @Column(name = "ROLES", nullable = false)
    private String roles;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private UserType userType;

    @Column(name = "ENABLED", nullable = false)
    @ColumnDefault("true")
    private Boolean enabled;

    @Column(name = "NOT_LOCKED", nullable = false)
    @ColumnDefault("false")
    private Boolean notLocked;

    @CreatedDate
    @Column(name = "CREATED_DATE", updatable = false)
    private LocalDate createdDate;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    private LocalDate lastModifiedDate;
}
