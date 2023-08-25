package com.turingia.practica.repositories;

import com.turingia.practica.model.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByName(String name);

    @Query("select u from UserEntity u where u.name = ?1")
    Optional<UserEntity> getName(String name);
}
