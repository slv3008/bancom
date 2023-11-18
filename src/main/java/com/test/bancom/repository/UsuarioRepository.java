package com.test.bancom.repository;

import com.test.bancom.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método para buscar usuarios por nombre
    List<Usuario> findByName(String name);

    // Método para buscar usuarios por apellido
    List<Usuario> findByLastName(String lastName);

    // Método para buscar un usuario por número de teléfono
    Optional<Usuario> findByCellphone(String cellphone);
}
