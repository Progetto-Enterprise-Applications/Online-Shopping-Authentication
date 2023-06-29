package com.enterpriseapplications.authenticationspring.service.interfaces;

import com.enterpriseapplications.authenticationspring.dto.UserDto;
import com.enterpriseapplications.authenticationspring.entities.enums.UserType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService
{
    Page<UserDto> findAll(Pageable pageable);
    Page<UserDto> findByType(UserType userType,Pageable pageable);
    Page<UserDto> findByRoles(String roles,Pageable pageable);
    UserDto findByEmail(String email);
    UserDto insertUser(UserDto userDto);
    UserDto updateUser(Long id,UserDto userDto);
    void delete(Long id);
}
