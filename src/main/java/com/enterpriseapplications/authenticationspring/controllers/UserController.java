package com.enterpriseapplications.authenticationspring.controllers;

import com.enterpriseapplications.authenticationspring.dto.PaginationResponse;
import com.enterpriseapplications.authenticationspring.dto.UserDto;
import com.enterpriseapplications.authenticationspring.entities.enums.UserType;
import com.enterpriseapplications.authenticationspring.service.interfaces.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    //TODO Aggiungere i pre authorize

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<PaginationResponse<UserDto>> findAll(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        Page<UserDto> users = this.userService.findAll(PageRequest.of(page, pageSize));
        return ResponseEntity.ok(new PaginationResponse<>(users.toList(), page, pageSize, users.getTotalPages(), users.getTotalElements()));
    }

    @GetMapping("/all/type/{type}")
    public ResponseEntity<PaginationResponse<UserDto>> findAll(@PathVariable("type") UserType userType, @RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        Page<UserDto> users = this.userService.findByType(userType, PageRequest.of(page, pageSize));
        return ResponseEntity.ok(new PaginationResponse<>(users.toList(), page, pageSize, users.getTotalPages(), users.getTotalElements()));
    }

    @GetMapping("/all/roles/{roles}")
    public ResponseEntity<PaginationResponse<UserDto>> findAll(@PathVariable("roles") String roles, @RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        Page<UserDto> users = this.userService.findByRoles(roles, PageRequest.of(page, pageSize));
        return ResponseEntity.ok(new PaginationResponse<>(users.toList(), page, pageSize, users.getTotalPages(), users.getTotalElements()));
    }

    @GetMapping
    public ResponseEntity<UserDto> findByEmail(@RequestParam("email") @Email String email) {
        return ResponseEntity.ok(this.userService.findByEmail(email));
    }

    @PostMapping
    public ResponseEntity<UserDto> insertUser(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(this.userService.insertUser(userDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id,@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(this.userService.updateUser(id,userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
