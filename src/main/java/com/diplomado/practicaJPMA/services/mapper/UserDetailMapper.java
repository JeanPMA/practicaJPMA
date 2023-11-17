package com.diplomado.practicaJPMA.services.mapper;

import com.diplomado.practicaJPMA.domain.entities.Rol;
import com.diplomado.practicaJPMA.domain.entities.UserDetail;
import com.diplomado.practicaJPMA.dto.RolDTO;
import com.diplomado.practicaJPMA.dto.UserDetailDTO;
import org.springframework.stereotype.Component;

@Component
public class UserDetailMapper implements CustomMapper<UserDetailDTO, UserDetail>{
    @Override
    public UserDetailDTO toDto(UserDetail userDetail) {
        UserDetailDTO userDetailDTO = new UserDetailDTO();
        userDetailDTO.setId(userDetail.getId());
        userDetailDTO.setFirst_name(userDetail.getFirst_name());
        userDetailDTO.setLast_name(userDetail.getLast_name());
        userDetailDTO.setAge(userDetail.getAge());
        userDetailDTO.setBirth_day(userDetail.getBirth_day());
        userDetailDTO.setUsuario(userDetail.getUser());

        return userDetailDTO;
    }

    @Override
    public UserDetail toEntity(UserDetailDTO userDetailDTO) {
        UserDetail userDetail = new UserDetail();
        userDetail.setId(userDetailDTO.getId());
        userDetail.setFirst_name(userDetailDTO.getFirst_name());
        userDetail.setLast_name(userDetailDTO.getLast_name());
        userDetail.setAge(userDetailDTO.getAge());
        userDetail.setBirth_day(userDetailDTO.getBirth_day());
        userDetail.setUser(userDetailDTO.getUsuario());

        return userDetail;
    }
}
