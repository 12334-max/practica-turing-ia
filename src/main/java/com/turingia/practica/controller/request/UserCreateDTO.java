package com.turingia.practica.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    private Set<String> role;
}
