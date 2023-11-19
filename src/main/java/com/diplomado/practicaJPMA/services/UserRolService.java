package com.diplomado.practicaJPMA.services;


import com.diplomado.practicaJPMA.domain.entities.UserRol;
import com.diplomado.practicaJPMA.domain.entities.Usuario;
import com.diplomado.practicaJPMA.dto.UserRolDTO;

import java.util.List;
import java.util.Optional;

public interface UserRolService {
    List<UserRolDTO> listUserRoles();
    UserRol parcial(UserRolDTO dto, Long id);
}
