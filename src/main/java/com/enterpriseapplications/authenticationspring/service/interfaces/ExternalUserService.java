package com.enterpriseapplications.authenticationspring.service.interfaces;

import com.enterpriseapplications.authenticationspring.dto.ExternalUserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.core.user.OAuth2User;


public interface ExternalUserService
{
    Page<ExternalUserDto> findAll(Pageable pageable);
    ExternalUserDto registerExternalUser(OAuth2User oAuth2User);
    ExternalUserDto find(Long id,String externalID);
    ExternalUserDto insertUser(ExternalUserDto externalUserDto);
    ExternalUserDto updateUser(Long id,ExternalUserDto externalUserDto);
    void delete(Long id);
}
