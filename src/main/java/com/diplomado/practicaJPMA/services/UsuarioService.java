package com.diplomado.practicaJPMA.services;


import com.diplomado.practicaJPMA.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<UsuarioDTO> listUsers();
    List<UsuarioDTO> listUsersDetailed();
    UsuarioDTO save(UsuarioDTO dto);
    Optional<UsuarioDTO> getUserById(Integer id);
    void delete(Integer id);
}
