package com.enterpriseapplications.authenticationspring.config.jwt;

import com.enterpriseapplications.authenticationspring.config.auth.ILoggedUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class GenericTokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {

    private static final Set<String> ID_TOKEN_CLAIMS = Set.of(IdTokenClaimNames.ISS, IdTokenClaimNames.SUB, IdTokenClaimNames.AUD, IdTokenClaimNames.EXP, IdTokenClaimNames.IAT, IdTokenClaimNames.AUTH_TIME, IdTokenClaimNames.NONCE, IdTokenClaimNames.ACR, IdTokenClaimNames.AMR, IdTokenClaimNames.AZP, IdTokenClaimNames.AT_HASH, IdTokenClaimNames.C_HASH);

    @Override
    public void customize(JwtEncodingContext context) {

        if(context.getPrincipal().getPrincipal() instanceof ILoggedUser loggedUser)
        {
            context.getClaims().claim("sub", loggedUser.getId());
            context.getClaims().claim("email", loggedUser.getEmail());
            context.getClaims().claim("username", loggedUser.getName());
            context.getClaims().claim("roles", loggedUser.getRoles());
        }

    }


}
