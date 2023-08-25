package com.turingia.practica.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String title;

    @NotBlank
    private String autor;

    @NotBlank
    private String description;

    @NotBlank
    private String image;
}
