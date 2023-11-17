package com.diplomado.practicaJPMA.services.mapper;

import com.diplomado.practicaJPMA.domain.entities.UserRol;
import com.diplomado.practicaJPMA.domain.entities.Usuario;
import com.diplomado.practicaJPMA.dto.UserRolDTO;
import com.diplomado.practicaJPMA.dto.UsuarioDTO;
import org.springframework.stereotype.Component;

@Component
public class UserRolMapper implements CustomMapper<UserRolDTO, UserRol>{
    @Override
    public UserRolDTO toDto(UserRol userRol) {
        UserRolDTO userRolDTO = new UserRolDTO();
        userRolDTO.setId(userRol.getId());
        userRolDTO.setActive(userRol.getActive());
        userRolDTO.setCreated_at(userRol.getCreated_at());
        userRolDTO.setUsuario(userRol.getUser());
        userRolDTO.setRol(userRol.getRol());

        return userRolDTO;
    }

    @Override
    public UserRol toEntity(UserRolDTO userRolDTO) {
        UserRol userRol = new UserRol();
        userRol.setId(userRolDTO.getId());
        userRol.setActive(userRolDTO.getActive());
        userRol.setCreated_at(userRolDTO.getCreated_at());
        userRol.setUser(userRolDTO.getUsuario());
        userRol.setRol(userRolDTO.getRol());

        return userRol;
    }
}
