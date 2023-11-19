package com.diplomado.practicaJPMA.repositories;


import com.diplomado.practicaJPMA.domain.entities.UserRol;
import com.diplomado.practicaJPMA.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRolRepository extends JpaRepository<UserRol, Long> {
    @Query("SELECT ur FROM UserRol ur WHERE ur.usuario = :user")
    List<UserRol> findByUser(@Param("user") Usuario usuario);
}
