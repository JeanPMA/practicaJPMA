package com.diplomado.practicaJPMA.services;


import com.diplomado.practicaJPMA.domain.entities.Usuario;
import com.diplomado.practicaJPMA.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UsuarioService {
    List<UsuarioDTO> listUsers();
    List<UsuarioDTO> listUsersDetailed();
    UsuarioDTO save(UsuarioDTO dto);
    Optional<UsuarioDTO> getUserById(Integer id);
    UsuarioDTO editarUsuario( Integer id, UsuarioDTO dto);

    void delete(Integer id);
}
