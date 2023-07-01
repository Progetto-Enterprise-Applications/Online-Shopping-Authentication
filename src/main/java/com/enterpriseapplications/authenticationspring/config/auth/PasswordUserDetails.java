package com.enterpriseapplications.authenticationspring.config.auth;

import com.enterpriseapplications.authenticationspring.dto.LocalUserDto;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Data
public class PasswordUserDetails implements UserDetails, ILoggedUser
{
    private String id;
    private String email;
    private String username;
    private String password;
    private boolean enabled;
    private boolean notLocked;
    private List<GrantedAuthority> permissions;
    private List<String> roles;

    public PasswordUserDetails(LocalUserDto userDto) {
        this.permissions = Arrays.stream(userDto.getRoles().split(",")).map(role -> "ROLE_" + role).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        this.email = userDto.getEmail();
        this.username = userDto.getUsername();
        this.password = userDto.getPassword();
        this.enabled = userDto.getEnabled();
        this.notLocked = userDto.getNotLocked();
        this.email = userDto.getEmail();
        this.id = String.valueOf(userDto.getId());
        this.roles = Arrays.stream(userDto.getRoles().split(",")).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return notLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getName() {
        return this.username;
    }

    @Override
    public List<String> getRoles() {
        return this.roles;
    }
}
