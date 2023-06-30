package com.enterpriseapplications.authenticationspring.controllers;

import com.enterpriseapplications.authenticationspring.dto.ExternalUserDto;
import com.enterpriseapplications.authenticationspring.dto.PaginationResponse;
import com.enterpriseapplications.authenticationspring.entities.enums.ExternalProvider;
import com.enterpriseapplications.authenticationspring.service.interfaces.ExternalUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/externalUsers")
@RequiredArgsConstructor
public class ExternalUserController {

    //TODO Aggiungere i pre authorize

    private final ExternalUserService externalUserService;

    @GetMapping("/all")
    public ResponseEntity<PaginationResponse<ExternalUserDto>> findAll(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        Page<ExternalUserDto> users = this.externalUserService.findAll(PageRequest.of(page, pageSize));
        return ResponseEntity.ok(new PaginationResponse<>(users.toList(), page, pageSize, users.getTotalPages(), users.getTotalElements()));
    }

    @GetMapping("/all/provider/{provider}")
    public ResponseEntity<PaginationResponse<ExternalUserDto>> findAll(@PathVariable("provider") ExternalProvider externalProvider, @RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        Page<ExternalUserDto> users = this.externalUserService.findAll(PageRequest.of(page,pageSize));
        return ResponseEntity.ok(new PaginationResponse<>(users.toList(),page,pageSize,users.getTotalPages(),users.getTotalElements()));
    }

    @GetMapping
    public ResponseEntity<ExternalUserDto> find(@RequestParam(value = "id",required = false) Long id,@RequestParam(value = "externalID",required = false) Long externalID) {
        return ResponseEntity.ok(this.externalUserService.find(id,externalID));
    }

    @PostMapping
    public ResponseEntity<ExternalUserDto> insertUser(@RequestBody @Valid ExternalUserDto externalUserDto) {
        return ResponseEntity.ok(this.externalUserService.insertUser(externalUserDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExternalUserDto> updateUser(@PathVariable("id") Long id,@RequestBody @Valid ExternalUserDto externalUserDto) {
        return ResponseEntity.ok(this.externalUserService.updateUser(id,externalUserDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ExternalUserDto> delete(@PathVariable("id") Long id) {
        this.externalUserService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
