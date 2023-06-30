package com.enterpriseapplications.authenticationspring.controllers;

import com.enterpriseapplications.authenticationspring.dto.LocalUserDto;
import com.enterpriseapplications.authenticationspring.dto.PaginationResponse;
import com.enterpriseapplications.authenticationspring.dto.UserDto;
import com.enterpriseapplications.authenticationspring.service.interfaces.LocalUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/localUsers")
@RequiredArgsConstructor
public class LocalUserController {

    //TODO Aggiungere i pre authorize

    private final LocalUserService localUserService;

    @GetMapping("/all")
    public ResponseEntity<PaginationResponse<LocalUserDto>> findAll(@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize) {
        Page<LocalUserDto> users = this.localUserService.findAll(PageRequest.of(page,pageSize));
        return ResponseEntity.ok(new PaginationResponse<>(users.toList(),page,pageSize,users.getTotalPages(),users.getTotalElements()));
    }

    @GetMapping
    public ResponseEntity<LocalUserDto> find(@RequestParam(value = "id",required = false) Long id,@RequestParam(value = "username",required = false) String username) {
        return ResponseEntity.ok(this.localUserService.find(id,username));
    }

    @PostMapping
    public ResponseEntity<LocalUserDto> insertUser(@RequestBody @Valid LocalUserDto localUserDto) {
        return ResponseEntity.ok(this.localUserService.insertUser(localUserDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalUserDto> updateUser(@PathVariable("id") Long id,@RequestBody @Valid LocalUserDto localUserDto) {
        return ResponseEntity.ok(this.localUserService.updateUser(id,localUserDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LocalUserDto> deleteUser(@PathVariable("id") Long id) {
        this.localUserService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
