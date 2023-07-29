package com.enterpriseapplications.authenticationspring.controllers;

import com.enterpriseapplications.authenticationspring.dto.LocalUserDto;
import com.enterpriseapplications.authenticationspring.dto.PaginationResponse;
import com.enterpriseapplications.authenticationspring.dto.RegisterUser;
import com.enterpriseapplications.authenticationspring.dto.UserDto;
import com.enterpriseapplications.authenticationspring.entities.enums.UserType;
import com.enterpriseapplications.authenticationspring.service.interfaces.LocalUserService;
import com.enterpriseapplications.authenticationspring.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
public class UserController {

    private final UserService userService;
    private final LocalUserService localUserService;

    @PreAuthorize("@ownerPermissionEvaluator.checkRole(T(com.enterpriseapplications.authenticationspring.config.RoleType).ADMIN)")
    @GetMapping("/all")
    public ResponseEntity<PaginationResponse<UserDto>> findAll(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        Page<UserDto> users = this.userService.findAll(PageRequest.of(page, pageSize));
        return ResponseEntity.ok(new PaginationResponse<>(users.toList(), page, pageSize, users.getTotalPages(), users.getTotalElements()));
    }

    @PreAuthorize("@ownerPermissionEvaluator.checkRole(T(com.enterpriseapplications.authenticationspring.config.RoleType).ADMIN)")
    @GetMapping("/all/type/{type}")
    public ResponseEntity<PaginationResponse<UserDto>> findAll(@PathVariable("type") UserType userType, @RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        Page<UserDto> users = this.userService.findByType(userType, PageRequest.of(page, pageSize));
        return ResponseEntity.ok(new PaginationResponse<>(users.toList(), page, pageSize, users.getTotalPages(), users.getTotalElements()));
    }

    @PreAuthorize("@ownerPermissionEvaluator.checkRole(T(com.enterpriseapplications.authenticationspring.config.RoleType).ADMIN)")
    @GetMapping("/all/roles/{roles}")
    public ResponseEntity<PaginationResponse<UserDto>> findAll(@PathVariable("roles") String roles, @RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        Page<UserDto> users = this.userService.findByRoles(roles, PageRequest.of(page, pageSize));
        return ResponseEntity.ok(new PaginationResponse<>(users.toList(), page, pageSize, users.getTotalPages(), users.getTotalElements()));
    }

    @PreAuthorize("@ownerPermissionEvaluator.checkRole(T(com.enterpriseapplications.authenticationspring.config.RoleType).ADMIN)")
    @GetMapping
    public ResponseEntity<UserDto> findByEmail(@RequestParam("email") @Email String email) {
        return ResponseEntity.ok(this.userService.findByEmail(email));
    }
    @PostMapping("/register")
    public ResponseEntity<LocalUserDto> insertUser(@RequestBody @Valid RegisterUser localUserDto) {
        return ResponseEntity.ok(this.localUserService.insertUser(localUserDto));
    }

    @PreAuthorize("@ownerPermissionEvaluator.hasPermission(#id,T(com.enterpriseapplications.authenticationspring.config.RoleType).BASIC)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
