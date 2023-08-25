package com.cursojava.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@ToString
@Table(name = "usuarios")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private long id;

    @NotNull(message = "Campo requerido")
    @Getter @Setter @Column(name = "name")
    private String name;

    @NotNull(message = "Campo requerido")
    @Getter @Setter @Column(name = "lastname")
    private String lastname;

    @Min(value = 10, message = "Tienen que se de 10 digitos")
    @Getter @Setter @Column(name = "phone")
    private String phone;

    @NotNull(message = "Campo requerido")
    @Getter @Setter @Column(name = "email")
    private String email;

    @NotNull(message = "Campo requerido")
    @Getter @Setter @Column(name = "password")
    private String password;

}
