package com.cursojava.demo.dao;

import com.cursojava.demo.model.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class AuthDaoImp implements AuthDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserforCredentials(User user) {
        String query = "FROM User WHERE email= :email";
        List<User> lista = entityManager.createQuery(query)
                .setParameter("email", user.getEmail())
                .getResultList();

        if (lista.isEmpty()){
            return null;
        }

        String passwordVerify = lista.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if(argon2.verify(passwordVerify, user.getPassword())){
            return lista.get(0);
        }
        return null;
    }
}
