package com.diplomado.practicaJPMA.repositories;


import com.diplomado.practicaJPMA.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
