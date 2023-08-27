package com.turingia.practica.controller;

import com.turingia.practica.controller.request.UserCreateDTO;
import com.turingia.practica.model.ERole;
import com.turingia.practica.model.RoleEntity;
import com.turingia.practica.model.UserEntity;
import com.turingia.practica.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //esta metodo realizar el registro de un usuario y que rol tendra.
    @PostMapping("/addUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody UserCreateDTO userCreateDTO){
        Set<RoleEntity> roles = userCreateDTO.getRole().stream()
                        .map(role -> RoleEntity.builder()
                                .name(ERole.valueOf(role))
                                .build())
                .collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
                .username(userCreateDTO.getUsername())
                .password(passwordEncoder.encode(userCreateDTO.getPassword()))
                .email(userCreateDTO.getEmail())
                .roles(roles)
                .build();
        userRepository.save(userEntity);

        return ResponseEntity.ok(userEntity);
    }

}
