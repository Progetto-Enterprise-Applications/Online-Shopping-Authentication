package com.enterpriseapplications.authenticationspring.service.interfaces;

import com.enterpriseapplications.authenticationspring.dto.LocalUserDto;
import com.enterpriseapplications.authenticationspring.dto.RegisterUserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LocalUserService
{
    Page<LocalUserDto> findAll(Pageable pageable);
    LocalUserDto find(Long id,String username);
    LocalUserDto insertUser(RegisterUserDto localUserDto);
    LocalUserDto updateUser(Long id,LocalUserDto localUserDto);
    void delete(Long id);
}
