package com.enterpriseapplications.authenticationspring.service.implementations;

import com.enterpriseapplications.authenticationspring.dao.ExternalUserDao;
import com.enterpriseapplications.authenticationspring.dao.UserDao;
import com.enterpriseapplications.authenticationspring.dto.ExternalUserDto;
import com.enterpriseapplications.authenticationspring.entities.ExternalUser;
import com.enterpriseapplications.authenticationspring.entities.enums.ExternalProvider;
import com.enterpriseapplications.authenticationspring.entities.enums.UserType;
import com.enterpriseapplications.authenticationspring.service.interfaces.ExternalUserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class ExternalUserServiceImp implements ExternalUserService  {

    private final ExternalUserDao externalUserDao;
    private final UserDao userDao;
    private final ModelMapper modelMapper;

    public ExternalUserServiceImp(ExternalUserDao externalUserDao,UserDao userDao,ModelMapper modelMapper) {
        this.externalUserDao = externalUserDao;
        this.userDao = userDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<ExternalUserDto> findAll(Pageable pageable) {
        Page<ExternalUser> externalUsers = this.externalUserDao.findAll(pageable);
        return new PageImpl<>(externalUsers.stream().map(user -> this.modelMapper.map(user,ExternalUserDto.class)).collect(Collectors.toList()),pageable,externalUsers.getTotalElements());
    }

    @Override
    public ExternalUserDto registerExternalUser(OAuth2User oAuth2User) {



        String subject = oAuth2User.getAttribute("sub");

        ExternalUser externalUser = this.externalUserDao.findByExternalID(subject).orElseGet(() -> {
            ExternalUser newUser = new ExternalUser();
            newUser.setExternalID(subject);
            newUser.setUsername(oAuth2User.getAttribute("given_name"));
            newUser.setEmail(oAuth2User.getAttribute("email"));
            newUser.setExternalProvider(ExternalProvider.GOOGLE);
            newUser.setUserType(UserType.EXTERNAL);
            newUser.setEnabled(true);
            newUser.setNotLocked(true);
            newUser.setRoles("BASIC");
            return this.externalUserDao.save(newUser);
        });

        return this.modelMapper.map(externalUser,ExternalUserDto.class);
    }


    @Override
    public ExternalUserDto find(Long id, String externalID) {
        Optional<ExternalUser> externalUserOptional = Optional.empty();
        if(id != null && externalID != null)
            externalUserOptional = this.externalUserDao.find(id,externalID);
        else if(externalID != null)
            externalUserOptional = this.externalUserDao.findByExternalID(externalID);
        else if(id != null)
            externalUserOptional = this.externalUserDao.findById(id);
        return externalUserOptional.map(externalUser -> this.modelMapper.map(externalUser,ExternalUserDto.class)).orElseThrow();
    }

    @Override
    @Transactional
    public ExternalUserDto insertUser(ExternalUserDto externalUserDto) {
        ExternalUser externalUser = this.modelMapper.map(externalUserDto,ExternalUser.class);
        this.externalUserDao.save(externalUser);
        return this.modelMapper.map(externalUser,ExternalUserDto.class);
    }

    @Override
    @Transactional
    public ExternalUserDto updateUser(Long id, ExternalUserDto externalUserDto) {
        Optional<ExternalUser> externalUserOptional = this.externalUserDao.findById(id);
        return externalUserOptional.map(externalUser -> {
            //externalUser.setExternalID(id);
            return this.modelMapper.map(externalUser,ExternalUserDto.class);
        }).orElseThrow();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.externalUserDao.findById(id).orElseThrow();
        this.externalUserDao.deleteById(id);
    }
}
