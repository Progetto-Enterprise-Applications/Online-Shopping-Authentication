package com.enterpriseapplications.authenticationspring.service.implementations;

import com.enterpriseapplications.authenticationspring.dao.UserDao;
import com.enterpriseapplications.authenticationspring.dto.UserDto;
import com.enterpriseapplications.authenticationspring.entities.User;
import com.enterpriseapplications.authenticationspring.entities.enums.UserType;
import com.enterpriseapplications.authenticationspring.service.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.Optional;


@Service
public class UserServiceImp implements UserService
{
    private final UserDao userDao;
    private final ModelMapper modelMapper;

    public UserServiceImp(UserDao userDao,ModelMapper modelMapper) {
        this.userDao = userDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        Page<User> users = this.userDao.findAll(pageable);
        return new PageImpl<>(users.stream().map(user -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList()),pageable,users.getTotalElements());
    }

    @Override
    public Page<UserDto> findByType(UserType userType, Pageable pageable) {
        Page<User> users = this.userDao.findByType(userType,pageable);
        return new PageImpl<>(users.stream().map(user -> this.modelMapper.map(user,UserDto.class)).collect(Collectors.toList()),pageable,users.getTotalElements());
    }

    @Override
    public Page<UserDto> findByRoles(String roles, Pageable pageable) {
        Page<User> users = this.userDao.findByRoles(roles,pageable);
        return new PageImpl<>(users.stream().map(user -> this.modelMapper.map(user,UserDto.class)).collect(Collectors.toList()),pageable,users.getTotalElements());
    }

    @Override
    public UserDto findByEmail(String email) {
        Optional<User> userOptional = this.userDao.findByEmail(email);
        return userOptional.map(user -> this.modelMapper.map(user,UserDto.class)).orElseThrow();
    }

    @Override
    public UserDto insertUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto,User.class);
        this.userDao.save(user);
        return this.modelMapper.map(user,UserDto.class);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        Optional<User> userOptional = this.userDao.findById(id);
        return userOptional.map(user -> {
            user.setEmail(userDto.getEmail());
            return this.modelMapper.map(user,UserDto.class);
        }).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        this.userDao.findById(id).orElseThrow();
        this.userDao.deleteById(id);
    }
}
