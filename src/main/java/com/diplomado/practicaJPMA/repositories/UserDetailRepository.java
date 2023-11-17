package com.diplomado.practicaJPMA.repositories;


import com.diplomado.practicaJPMA.domain.entities.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {
}
