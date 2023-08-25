package com.cursojava.demo.controller.auth;

import com.cursojava.demo.dao.AuthDao;
import com.cursojava.demo.model.User;
import com.cursojava.demo.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthDao authDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public String login(@RequestBody User user){
        User getUser = authDao.getUserforCredentials(user);
        if (getUser != null){

            return jwtUtil.create(String.valueOf(getUser.getId()),getUser.getEmail());
        }
        return "Failed";
    }
}
