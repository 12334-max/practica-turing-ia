package com.turingia.practica.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/*
* esta clase sea donde recibamos el RequestBody
* del formulario
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String autor;

    @NotBlank
    private String description;

    @NotBlank
    private MultipartFile image;
}
