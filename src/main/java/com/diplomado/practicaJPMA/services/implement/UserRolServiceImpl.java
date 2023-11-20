package com.diplomado.practicaJPMA.services.implement;

import com.diplomado.practicaJPMA.domain.entities.UserRol;
import com.diplomado.practicaJPMA.domain.entities.Usuario;
import com.diplomado.practicaJPMA.dto.UserDetailDTO;
import com.diplomado.practicaJPMA.dto.UserRolDTO;
import com.diplomado.practicaJPMA.repositories.UserRolRepository;
import com.diplomado.practicaJPMA.services.UserRolService;
import com.diplomado.practicaJPMA.services.mapper.UserRolMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRolServiceImpl implements UserRolService {
    private final UserRolRepository userRolRepository;
    private final UserRolMapper userRolMapper;

    public UserRolServiceImpl(UserRolRepository userRolRepository, UserRolMapper userRolMapper) {
        this.userRolRepository = userRolRepository;
        this.userRolMapper = userRolMapper;
    }

    @Override
    public List<UserRolDTO> listUserRoles() {
        return userRolRepository.findAll()
                .stream()
                .map(userRolMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserRol parcial(UserRolDTO dto, Long id){
        UserRol userRol = userRolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UsuarioRol no encontrado " + id));

        if (dto.getActive() != null) {
            userRol.setActive(dto.getActive());
        }
        return userRolRepository.save(userRol);
    }


    public class ApplicationException extends RuntimeException {
        public ApplicationException(String message) {
            super(message);
        }

        public ApplicationException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    public class ResourceNotFoundException extends ApplicationException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
