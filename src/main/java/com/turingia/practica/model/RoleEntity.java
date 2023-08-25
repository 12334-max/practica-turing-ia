package com.turingia.practica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
*la tabla de roles ya con la realicones de usuario y el rol que tiene
*  */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ERole name;
}
