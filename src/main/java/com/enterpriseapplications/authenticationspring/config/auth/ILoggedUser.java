package com.enterpriseapplications.authenticationspring.config.auth;

import java.util.List;

public interface ILoggedUser {
    
    String getId();
    String getName();
    String getEmail();
    List<String> getRoles();
}
