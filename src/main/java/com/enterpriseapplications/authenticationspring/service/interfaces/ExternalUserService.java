package com.enterpriseapplications.authenticationspring.service.interfaces;

import com.enterpriseapplications.authenticationspring.dto.ExternalUserDto;
import com.enterpriseapplications.authenticationspring.entities.ExternalUser;
import com.enterpriseapplications.authenticationspring.entities.enums.ExternalProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ExternalUserService
{
    Page<ExternalUserDto> findAll(Pageable pageable);
    Page<ExternalUserDto> findByProvider(ExternalProvider externalProvider,Pageable pageable);
    ExternalUserDto find(Long id,Long externalID);
    ExternalUserDto insertUser(ExternalUserDto externalUserDto);
    ExternalUserDto updateUser(Long id,ExternalUserDto externalUserDto);
    void delete(Long id);
}
