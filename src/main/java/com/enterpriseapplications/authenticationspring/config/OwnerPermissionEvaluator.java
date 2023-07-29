package com.enterpriseapplications.authenticationspring.config;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class OwnerPermissionEvaluator {


    public Boolean hasPermission( Object ownerInfo, RoleType minimumRole)
    {
        Authentication authorization = SecurityContextHolder.getContext().getAuthentication();
        if(authorization instanceof JwtAuthenticationToken principal)
        {

            if(checkRole(RoleType.ADMIN))
                return true;

            if(!checkRole(minimumRole))
                return false;


            if(ownerInfo instanceof Long id)
            {
                return principal.getTokenAttributes().get("sub").equals(String.valueOf(id));
            }
            return false;
        }
        return false;

    }

    public Boolean checkRole(RoleType roleType)
    {
        Authentication authorization = SecurityContextHolder.getContext().getAuthentication();

        if(authorization != null) {
            Optional<RoleType> role = authorization.getAuthorities().stream().map(auth -> RoleType.parseFrom(auth.getAuthority().replace("ROLE_", ""))).filter(Objects::nonNull).findFirst();
            return role.map(roleType::compareRoles).orElse(false);
        }
        return false;
    }

}
