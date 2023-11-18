package com.test.bancom.controller;

import com.test.bancom.model.Post;
import com.test.bancom.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // GET - Listar todos los posts de un usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Post>> getAllPostsByUsuarioId(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(postService.getPostsByUsuarioId(usuarioId));
    }

    // POST - Crear un nuevo post
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.createPost(post));
    }

    // PUT - Actualizar un post
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post postDetails) {
        return postService.updatePost(id, postDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE - Eliminar un post
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok().build();
    }
}