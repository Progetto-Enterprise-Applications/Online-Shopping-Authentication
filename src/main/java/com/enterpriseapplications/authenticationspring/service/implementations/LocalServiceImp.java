package com.enterpriseapplications.authenticationspring.service.implementations;

import com.enterpriseapplications.authenticationspring.dao.LocalUserDao;
import com.enterpriseapplications.authenticationspring.dto.LocalUserDto;
import com.enterpriseapplications.authenticationspring.dto.UserDto;
import com.enterpriseapplications.authenticationspring.entities.LocalUser;
import com.enterpriseapplications.authenticationspring.entities.User;
import com.enterpriseapplications.authenticationspring.service.interfaces.LocalUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocalServiceImp implements LocalUserService {

    private final LocalUserDao localUserDao;
    private final ModelMapper modelMapper;

    public LocalServiceImp(LocalUserDao localUserDao,ModelMapper modelMapper) {
        this.localUserDao = localUserDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<LocalUserDto> findAll(Pageable pageable) {
        Page<LocalUser> users = this.localUserDao.findAll(pageable);
        return new PageImpl<>(users.stream().map(user -> this.modelMapper.map(user,LocalUserDto.class)).collect(Collectors.toList()),pageable,users.getTotalElements());
    }

    @Override
    public LocalUserDto findByID(Long id) {
        return this.modelMapper.map(this.localUserDao.findById(id),LocalUserDto.class);
    }

    @Override
    public LocalUserDto findByUsername(String username) {
        Optional<LocalUser> userOptional = this.localUserDao.findUserByUsername(username);
        return userOptional.map(user -> this.modelMapper.map(user,LocalUserDto.class)).orElseThrow();
    }

    @Override
    public LocalUserDto insertUser(LocalUserDto localUserDto) {
        LocalUser localUser = this.modelMapper.map(localUserDto,LocalUser.class);
        this.localUserDao.save(localUser);
        return this.modelMapper.map(localUser,LocalUserDto.class);
    }

    @Override
    public LocalUserDto updateUser(Long id, LocalUserDto localUserDto) {
        Optional<LocalUser> userOptional = this.localUserDao.findUserByUsername(localUserDto.getUsername());
        return userOptional.map(user -> {
            user.setUsername(localUserDto.getUsername());
            user.setPassword(localUserDto.getPassword());
            this.localUserDao.save(user);
            return this.modelMapper.map(user, LocalUserDto.class);
        }).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        this.localUserDao.findById(id).orElseThrow();
        this.localUserDao.deleteById(id);
    }
}
