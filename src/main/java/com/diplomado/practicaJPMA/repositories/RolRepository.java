package com.diplomado.practicaJPMA.repositories;

import com.diplomado.practicaJPMA.domain.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Integer> {
}
