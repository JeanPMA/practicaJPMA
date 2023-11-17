package com.diplomado.practicaJPMA.services.mapper;

import com.diplomado.practicaJPMA.domain.entities.UserDetail;
import com.diplomado.practicaJPMA.domain.entities.Usuario;
import com.diplomado.practicaJPMA.dto.UserDetailDTO;
import com.diplomado.practicaJPMA.dto.UsuarioDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper implements CustomMapper<UsuarioDTO, Usuario>{
    @Override
    public UsuarioDTO toDto(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setUsername(usuario.getUsername());
        usuarioDTO.setPassword(usuario.getPassword());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setCreated_at(usuario.getCreated_at());
        usuarioDTO.setUserDetail(usuario.getUserDetail());
        usuarioDTO.setRoles(usuario.getRoles());

        return usuarioDTO;
    }

    @Override
    public Usuario toEntity(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setCreated_at(usuarioDTO.getCreated_at());
        usuario.setUserDetail(usuarioDTO.getUserDetail());
        usuario.setRoles(usuarioDTO.getRoles());

        return usuario;
    }
}
