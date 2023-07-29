package com.enterpriseapplications.authenticationspring.service.interfaces;

import com.enterpriseapplications.authenticationspring.dto.LocalUserDto;
import com.enterpriseapplications.authenticationspring.dto.RegisterUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LocalUserService
{
    Page<LocalUserDto> findAll(Pageable pageable);
    LocalUserDto find(Long id,String username);
    LocalUserDto insertUser(RegisterUser localUserDto);
    LocalUserDto updateUser(Long id, RegisterUser localUserDto);
    void delete(Long id);
}
