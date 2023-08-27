package com.turingia.practica.controller;

import com.turingia.practica.controller.request.PostCreateDTO;
import com.turingia.practica.model.PostEntity;
import com.turingia.practica.repositories.PostRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    @Value("${file.upload-dir}")
    private String uploadDir;


    @GetMapping("/getPosts")
    public List<PostEntity> getPost(){
        return (List<PostEntity>) postRepository.findAll();
    }

    //funcion para agregar nuevos post
    @PostMapping(value = "/addPost")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> createPost(@Valid @ModelAttribute PostCreateDTO postCreateDTO) throws IOException {
        MultipartFile file = postCreateDTO.getImage();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path filePath = Paths.get(uploadDir, fileName);
        Files.write(filePath, file.getBytes());

        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/uploads/")
                .path(fileName)
                .toUriString();


        PostEntity postEntity = PostEntity.builder()
                .title(postCreateDTO.getTitle())
                .autor(postCreateDTO.getAutor())
                .description(postCreateDTO.getDescription())
                .image(fileUrl)
                .build();
        postRepository.save(postEntity);
        return ResponseEntity.ok("Se ha creado el post");
    }

    @DeleteMapping("/deletePost/{id}")
    @PreAuthorize("hasRole('USER')")
    public String delete(@RequestParam Long id){
        postRepository.deleteById(id);
        return "Success";
    }
}