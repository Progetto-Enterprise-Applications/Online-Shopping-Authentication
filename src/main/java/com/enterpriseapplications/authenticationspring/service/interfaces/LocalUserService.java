package com.enterpriseapplications.authenticationspring.service.interfaces;

import com.enterpriseapplications.authenticationspring.dto.LocalUserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LocalUserService
{
    Page<LocalUserDto> findAll(Pageable pageable);

    LocalUserDto findByID(Long id);
    LocalUserDto findByUsername(String username);
    LocalUserDto insertUser(LocalUserDto localUserDto);
    LocalUserDto updateUser(Long id,LocalUserDto localUserDto);
    void delete(Long id);
}
