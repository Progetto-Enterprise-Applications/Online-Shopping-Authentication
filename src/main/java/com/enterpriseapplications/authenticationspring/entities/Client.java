package com.enterpriseapplications.authenticationspring.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "CLIENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private String id;

    @Column(name = "CLIENT_ID",unique = false)
    private String clientID;

    @Column(name = "CLIENT_ID_INSTANT_REGISTRATION",unique = false,nullable = false)
    private Instant clientIDIssuedAt;

    @Column(name = "CLIENT_SECRET",unique = false,nullable = false)
    private String clientSecret;

    @Column(name = "CLIENT_SECRET_EXPIRATION_DATE",unique = false,nullable = false)
    private Instant clientExpirationDate;

    @Column(name = "CLIENT_NAME",unique = false,nullable = false)
    private String clientName;

    @Column(name = "AUTHENTICATION_METHODS",length = 1000)
    private String clientAuthenticationMethods;

    @Column(name = "AUTHORIZATION_GRANT_TYPES",length = 1000)
    private String authorizationGrantTypes;

    @Column(name = "REDIRECT_URIS",length = 1000)
    private String redirectURIs;

    @Column(name = "POST_LOGOUT_REDIRECT_URIS",length = 1000)
    private String postLogoutRedirectUris;

    @Column(name = "SCOPES",length = 1000)
    private String scopes;

    @Column(name = "CLIENT_SETTINGS",length = 2000)
    private String clientSettings;

    @Column(name = "TOKEN_SETTINGS",length = 2000)
    private String tokenSettings;
}
