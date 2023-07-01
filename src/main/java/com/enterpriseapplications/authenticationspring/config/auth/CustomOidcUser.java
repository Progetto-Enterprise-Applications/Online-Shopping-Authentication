package com.enterpriseapplications.authenticationspring.config.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.Collection;
import java.util.List;
import java.util.Map;


@Data
@AllArgsConstructor
public class CustomOidcUser implements OidcUser, ILoggedUser {

    private final OidcUser oidcUser;
    private final String email;
    private final String name;
    private final String id;
    private final List<String> roles;

    @Override
    public Map<String, Object> getClaims() {
        return oidcUser.getClaims();
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return oidcUser.getUserInfo();
    }

    @Override
    public OidcIdToken getIdToken() {
        return oidcUser.getIdToken();
    }

    @Override
    public <A> A getAttribute(String name) {
        return OidcUser.super.getAttribute(name);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oidcUser.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oidcUser.getAuthorities();
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getRoles() {
        return this.roles;
    }
}
