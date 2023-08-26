package com.turingia.practica.repositories;

import com.turingia.practica.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String name);

    @Query("select u from UserEntity u where u.username = ?1")
    Optional<UserEntity> getNameUser(String name);
}
