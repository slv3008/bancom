package com.test.bancom.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
