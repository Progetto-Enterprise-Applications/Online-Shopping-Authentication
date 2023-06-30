package com.enterpriseapplications.authenticationspring.service;

import com.enterpriseapplications.authenticationspring.LoggedUserDetails;
import com.enterpriseapplications.authenticationspring.dto.LocalUserDto;
import com.enterpriseapplications.authenticationspring.service.interfaces.LocalUserService;
import com.enterpriseapplications.authenticationspring.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoggedUserDetailsService implements UserDetailsService
{
    private final LocalUserService localUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new LoggedUserDetails(this.localUserService.find(null,username));
    }
}
