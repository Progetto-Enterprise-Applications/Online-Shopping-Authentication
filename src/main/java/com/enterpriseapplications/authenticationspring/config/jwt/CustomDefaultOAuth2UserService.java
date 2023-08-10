package com.enterpriseapplications.authenticationspring.config.jwt;

import com.enterpriseapplications.authenticationspring.config.auth.CustomOidcUser;
import com.enterpriseapplications.authenticationspring.dto.ExternalUserDto;
import com.enterpriseapplications.authenticationspring.service.interfaces.ExternalUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class CustomDefaultOAuth2UserService extends OidcUserService {

    
    private final ExternalUserService externalUserService;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        ExternalUserDto externalUserDto = externalUserService.registerExternalUser(oidcUser);
        List<String> roles = Arrays.stream(externalUserDto.getRoles().split(",")).toList();
        return new CustomOidcUser(oidcUser,externalUserDto.getEmail(),externalUserDto.getUsername(),String.valueOf(externalUserDto.getId()),roles);

    }
}
