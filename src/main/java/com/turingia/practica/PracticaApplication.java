package com.turingia.practica;

import com.turingia.practica.model.ERole;
import com.turingia.practica.model.RoleEntity;
import com.turingia.practica.model.UserEntity;
import com.turingia.practica.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class PracticaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticaApplication.class, args);
	}

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;
	@Bean
	CommandLineRunner init(){
		return args -> {
			UserEntity userEntity = UserEntity.builder()
					.username("Javier")
					.email("javi123@gmail.com")
					.password(passwordEncoder.encode("javi123"))
					.roles(Set.of(RoleEntity.builder()
									.name(ERole.valueOf(ERole.ADMIN.name()))
							.build()))
					.build();
			UserEntity userEntity2 = UserEntity.builder()
					.username("Juan")
					.email("juan123@gmail.com")
					.password(passwordEncoder.encode("juan123"))
					.roles(Set.of(RoleEntity.builder()
							.name(ERole.valueOf(ERole.USER.name()))
							.build()))
					.build();

			userRepository.save(userEntity);
			userRepository.save(userEntity2);
		};
	}
}
