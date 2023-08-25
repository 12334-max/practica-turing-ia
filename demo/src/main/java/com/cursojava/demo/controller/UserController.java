package com.cursojava.demo.controller;

import com.cursojava.demo.dao.UserDao;
import com.cursojava.demo.model.User;
import com.cursojava.demo.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    boolean validateToken(String token){
        String userId = jwtUtil.getKey(token);
        return userId != null;
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    public List<User> getUsers(@RequestHeader(value = "Authorization") String token ){
        if (!validateToken(token)){return null;}
        return userDao.getUsers();
    }

    @RequestMapping( value = "/api/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id, @RequestHeader(value = "Authorization") String token ){
        if (!validateToken(token)){return;}
        userDao.deleteUser(id);
    }

    @RequestMapping(value = "/api/create", method = RequestMethod.POST)
    public String create(@RequestBody @Valid User user, @NotNull BindingResult bindingResult){
            if (bindingResult.hasErrors()){
                return "Fallido :(";
            }
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            String hash = argon2.hash(2,124,1, user.getPassword());
            user.setPassword(hash);
            userDao.register(user);
            return "OK";



    }

/*

    public User update(){
        return ;
    }

    public List<User> query(){
        return [];
    }*/
}
