package com.turingia.practica.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/*
* Este en la entida de usuario que se creara en la db
* **/

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //realizaoms las validaciones que sea un email, que no benga vacio y el tamaño de caracteres
    @Email @NotBlank @Size(max = 80)
    private String email;

    @NotBlank @Size(max = 30)
    private String name;

    @NotBlank
    private String password;
/*
* para crear el rol utlizamos el JOIN INNER para relacionar el id del
* usuario con el id del rol y asi asignar roles* */
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleEntity.class, cascade = CascadeType.PERSIST)
    private Set<RoleEntity> roles;
}
