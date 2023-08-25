package com.turingia.practica.controller;

import com.turingia.practica.controller.request.PostCreateDTO;
import com.turingia.practica.model.PostEntity;
import com.turingia.practica.repositories.PostRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;
    static final String UPLOAD_DIR = "uploads/";

    @GetMapping("/getPosts")
    public List<PostEntity> getPost(){
        return (List<PostEntity>) postRepository.findAll();
    }

    //funcion para agregar nuevos post
    @PostMapping("/addPost")
    public ResponseEntity<?> createPost(@Valid @RequestBody PostCreateDTO postCreateDTO) throws IOException {
        String filename = StringUtils.cleanPath(Objects.requireNonNull(postCreateDTO.getImage().getOriginalFilename()));
        Path path = Paths.get(UPLOAD_DIR+filename);
        Files.copy(postCreateDTO.getImage().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/uploads/")
                .path(filename)
                .toUriString();

        PostEntity postEntity = PostEntity.builder()
                .title(postCreateDTO.getTitle())
                .autor(postCreateDTO.getAutor())
                .description(postCreateDTO.getDescription())
                .image(fileUrl)
                .build();
        postRepository.save(postEntity);
        return ResponseEntity.ok(postEntity);
    }
}