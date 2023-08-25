package com.turingia.practica.repositories;

import com.turingia.practica.model.PostEntity;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<PostEntity, Long> {
}
