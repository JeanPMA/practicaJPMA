package com.diplomado.practicaJPMA.services;


import com.diplomado.practicaJPMA.dto.UserRolDTO;

import java.util.List;
import java.util.Optional;

public interface UserRolService {
    List<UserRolDTO> listUserRoles();
    UserRolDTO save(UserRolDTO dto);
    Optional<UserRolDTO> getUserRolById(Long id);
    void delete(Long id);
}
