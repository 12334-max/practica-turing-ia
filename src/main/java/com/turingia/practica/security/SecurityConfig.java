package com.turingia.practica.security;

import com.turingia.practica.jwt.JWTUtils;
import com.turingia.practica.security.filters.JwtAuthenticationFilter;
import com.turingia.practica.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    JWTUtils jwtUtils;
    @Autowired
    UserServiceImp userServiceImp;

    /*
    * configuracion de acceso a la pagina
    */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception{
        JwtAuthenticationFilter jwtAuthenticationFilter = new  JwtAuthenticationFilter(jwtUtils);
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->{
                    //estos urls seran publicos cualquiera puede acceder
                    auth.requestMatchers("/getPosts","/addUser").permitAll();
                    //agregarmos el filtro que protege los endpoints
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(session->{
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .addFilter(jwtAuthenticationFilter)
                .build();
    }


    /*
    * para no encriptar la contraseña
    */

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*
    * esto se encargara de la authentificacion en la pagina web*/
    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder) throws Exception {
        return  httpSecurity
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userServiceImp)
                .passwordEncoder(passwordEncoder())
                .and().build();
    }
}
