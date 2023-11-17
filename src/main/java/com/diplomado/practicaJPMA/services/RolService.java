package com.diplomado.practicaJPMA.services;

import com.diplomado.practicaJPMA.dto.RolDTO;

import java.util.List;
import java.util.Optional;

public interface RolService {
    List<RolDTO> listRoles();
    RolDTO save(RolDTO dto);
    Optional<RolDTO> getRolById(Integer id);
    void delete(Integer id);
}
