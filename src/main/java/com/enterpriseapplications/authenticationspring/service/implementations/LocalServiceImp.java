package com.enterpriseapplications.authenticationspring.service.implementations;

import com.enterpriseapplications.authenticationspring.dao.LocalUserDao;
import com.enterpriseapplications.authenticationspring.dao.UserDao;
import com.enterpriseapplications.authenticationspring.dto.LocalUserDto;
import com.enterpriseapplications.authenticationspring.dto.RegisterUser;
import com.enterpriseapplications.authenticationspring.entities.LocalUser;
import com.enterpriseapplications.authenticationspring.entities.enums.UserType;
import com.enterpriseapplications.authenticationspring.service.interfaces.LocalUserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocalServiceImp implements LocalUserService {

    private final LocalUserDao localUserDao;
    private final UserDao userDao;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public LocalServiceImp(LocalUserDao localUserDao, UserDao userDao, ModelMapper modelMapper, PasswordEncoder passwordEncoder)
    {
        this.localUserDao = localUserDao;
        this.userDao = userDao;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;

        LocalUser localUser = new LocalUser();
        localUser.setEmail("pippo@libero.it");
        localUser.setUsername("admin");
        localUser.setUserType(UserType.LOCAL);
        localUser.setEnabled(true);
        localUser.setRoles("ADMIN");
        localUser.setNotLocked(true);
        localUser.setPassword(this.passwordEncoder.encode("admin"));

        this.localUserDao.save(localUser);
    }


    @Override
    public Page<LocalUserDto> findAll(Pageable pageable) {
        Page<LocalUser> users = this.localUserDao.findAll(pageable);
        return new PageImpl<>(users.stream().map(user -> this.modelMapper.map(user,LocalUserDto.class)).collect(Collectors.toList()),pageable,users.getTotalElements());
    }

    @Override
    public LocalUserDto find(Long id, String username) {
        Optional<LocalUser> localUserOptional = Optional.empty();
        if(id != null && username != null)
            localUserOptional = this.localUserDao.findUser(id,username);
        else if(username != null)
            localUserOptional = this.localUserDao.findUserByUsername(username);
        else if(id != null)
            localUserOptional = this.localUserDao.findById(id);
        return localUserOptional.map(localUser -> this.modelMapper.map(localUser,LocalUserDto.class)).orElseThrow();
    }

    @Override
    @Transactional
    public LocalUserDto insertUser(RegisterUser localUserDto) {
        LocalUser localUser = this.modelMapper.map(localUserDto,LocalUser.class);
        localUser.setUserType(UserType.LOCAL);
        localUser.setEnabled(true);
        localUser.setRoles("BASIC");
        localUser.setNotLocked(true);
        localUser.setPassword(this.passwordEncoder.encode(localUserDto.getPassword()));
        return this.modelMapper.map(this.localUserDao.save(localUser),LocalUserDto.class);
    }

    @Override
    @Transactional
    public LocalUserDto updateUser(Long id, RegisterUser localUserDto) {


        Optional<LocalUser> userOptional = this.localUserDao.findUserByUsername(localUserDto.getUsername());
        return userOptional.map(user -> {
            user.setUsername(localUserDto.getUsername());
            user.setPassword(this.passwordEncoder.encode(localUserDto.getPassword()));
            this.localUserDao.save(user);
            return this.modelMapper.map(user, LocalUserDto.class);
        }).orElseThrow();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.localUserDao.findById(id).orElseThrow();
        this.localUserDao.deleteById(id);
    }
}
