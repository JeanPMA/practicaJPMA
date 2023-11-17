package com.diplomado.practicaJPMA.services;

import com.diplomado.practicaJPMA.dto.UserDetailDTO;

import java.util.List;
import java.util.Optional;

public interface UserDetailService {
    List<UserDetailDTO> listUserDetails();
    UserDetailDTO save(UserDetailDTO dto);
    Optional<UserDetailDTO> getUserDetailById(Integer id);
    void delete(Integer id);
}
