package com.enterpriseapplications.authenticationspring.service;

import com.enterpriseapplications.authenticationspring.config.auth.PasswordUserDetails;
import com.enterpriseapplications.authenticationspring.service.interfaces.LocalUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Component
public class LoggedUserDetailsService implements UserDetailsService
{
    private final LocalUserService localUserService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        try {
            return new PasswordUserDetails(this.localUserService.find(null,username));
        }
        catch (NoSuchElementException exception)
        {
            throw new UsernameNotFoundException(username);
        }
    }
}
