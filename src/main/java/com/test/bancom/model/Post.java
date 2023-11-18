package com.test.bancom.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

    @ManyToOne
    private Usuario usuario;

    // Getters
    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
