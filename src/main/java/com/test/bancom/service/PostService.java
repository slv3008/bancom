package com.test.bancom.service;

import com.test.bancom.model.Post;
import com.test.bancom.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // Obtener todos los posts de un usuario específico
    public List<Post> getPostsByUsuarioId(Long usuarioId) {
        return postRepository.findByUsuarioId(usuarioId);
    }

    // Crear un nuevo post
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    // Actualizar un post
    public Optional<Post> updatePost(Long id, Post postDetails) {
        return postRepository.findById(id)
                .map(post -> {
                    post.setText(postDetails.getText());
                    return postRepository.save(post);
                });
    }

    // Eliminar un post
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
