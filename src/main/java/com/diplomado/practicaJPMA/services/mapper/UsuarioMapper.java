package com.diplomado.practicaJPMA.services.mapper;

import com.diplomado.practicaJPMA.domain.entities.UserDetail;
import com.diplomado.practicaJPMA.domain.entities.Usuario;
import com.diplomado.practicaJPMA.dto.UserDetailDTO;
import com.diplomado.practicaJPMA.dto.UsuarioDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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


        return usuarioDTO;
    }


    public UsuarioDTO toDtoDetailed(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setUsername(usuario.getUsername());
        usuarioDTO.setPassword(usuario.getPassword());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setCreated_at(usuario.getCreated_at());

        if (usuario.getUserDetail() != null) {
            usuarioDTO.setFirst_name(usuario.getUserDetail().getFirst_name());
            usuarioDTO.setLast_name(usuario.getUserDetail().getLast_name());
            usuarioDTO.setAge(usuario.getUserDetail().getAge());
            usuarioDTO.setBirth_day(usuario.getUserDetail().getBirth_day());
        } else {
            usuarioDTO.setFirst_name("Not assigned yet");
            usuarioDTO.setLast_name("Not assigned yet");
            usuarioDTO.setAge(0);
            usuarioDTO.setBirth_day(LocalDate.now());

        }




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


        return usuario;
    }
}