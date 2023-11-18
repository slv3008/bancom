package com.test.bancom.repository;

import com.test.bancom.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // Método para encontrar todos los posts por el ID del usuario
    List<Post> findByUsuarioId(Long usuarioId);
}