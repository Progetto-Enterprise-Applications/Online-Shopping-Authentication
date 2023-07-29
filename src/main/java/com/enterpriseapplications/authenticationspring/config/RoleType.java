package com.enterpriseapplications.authenticationspring.config;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum RoleType {

    BASIC("BASIC",0),
    MODERATOR("MODERATOR",1),
    ADMIN("ADMIN",2);

    private final String role;
    private final int level;

    public Boolean compareRoles(RoleType roleType)
    {

        if(this.level < roleType.level)
            return true;
        else if(this.level > roleType.level)
            return false;
        else
            return this.equals(roleType);
    }

    public static RoleType parseFrom(String role)
    {

        for (RoleType roleEnum : RoleType.values()) {
            if(roleEnum.role.equals(role))
                return roleEnum;
        }
        return null;
    }
}
