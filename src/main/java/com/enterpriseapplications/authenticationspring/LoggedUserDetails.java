package com.enterpriseapplications.authenticationspring;

import com.enterpriseapplications.authenticationspring.dto.LocalUserDto;
import com.enterpriseapplications.authenticationspring.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Data
public class LoggedUserDetails implements UserDetails
{
    private Long id;
    private String email;
    private String username;
    private String password;
    private boolean enabled;
    private boolean notLocked;
    private List<GrantedAuthority> permissions;

    public LoggedUserDetails(LocalUserDto userDto) {
        this.permissions = Arrays.stream(userDto.getRoles().split(",")).map(role -> "ROLE_" + role).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        this.email = userDto.getEmail();
        this.username = userDto.getUsername();
        this.password = userDto.getPassword();
        this.enabled = userDto.getEnabled();
        this.notLocked = userDto.getNotLocked();
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
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return notLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
