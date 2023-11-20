package com.diplomado.practicaJPMA.services.implement;

import com.diplomado.practicaJPMA.domain.entities.Rol;
import com.diplomado.practicaJPMA.domain.entities.UserDetail;
import com.diplomado.practicaJPMA.domain.entities.UserRol;
import com.diplomado.practicaJPMA.domain.entities.Usuario;
import com.diplomado.practicaJPMA.dto.UsuarioDTO;
import com.diplomado.practicaJPMA.repositories.RolRepository;
import com.diplomado.practicaJPMA.repositories.UserDetailRepository;
import com.diplomado.practicaJPMA.repositories.UserRolRepository;
import com.diplomado.practicaJPMA.repositories.UsuarioRepository;
import com.diplomado.practicaJPMA.services.UsuarioService;
import com.diplomado.practicaJPMA.services.mapper.UsuarioMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {
    @PersistenceContext
    private EntityManager entityManager;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final UserDetailRepository userDetailRepository;

    private final UserRolRepository userRolRepository;
    private final RolRepository rolRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, UserDetailRepository userDetailRepository, UserRolRepository userRolRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.userDetailRepository = userDetailRepository;
        this.userRolRepository = userRolRepository;
        this.rolRepository = rolRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDTO> listUsers() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDTO> listUsersDetailed() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDtoDetailed).collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO save(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(dto.getPassword());
        usuario.setEmail(dto.getEmail());
        usuario.setCreated_at(LocalDateTime.now());

        usuarioRepository.save(usuario);

        if (dto.getFirst_name() != null && dto.getLast_name() != null && dto.getAge() != null && dto.getBirth_day() != null){
            UserDetail userDetail = new UserDetail();
            userDetail.setUser(usuario);
            userDetail.setFirst_name(dto.getFirst_name());
            userDetail.setLast_name(dto.getLast_name());
            userDetail.setAge(dto.getAge());
            userDetail.setBirth_day(dto.getBirth_day());
            userDetailRepository.save(userDetail);
        }

        if (dto.getRolesIds() != null){
            List<Rol> roles = rolRepository.findAllById(dto.getRolesIds());


            for (Rol rol : roles) {
                UserRol userRol = new UserRol();
                userRol.setUser(usuario);
                userRol.setRol(rol);
                userRol.setCreated_at(LocalDateTime.now());
                userRol.setActive(true);

                userRolRepository.save(userRol);
            }
        }
        return usuarioMapper.toDto(usuario);
    }
    public UsuarioDTO editarUsuario(Integer usuarioId, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado " + usuarioId));

        usuario.setUsername(dto.getUsername());
        usuario.setPassword(dto.getPassword());
        usuario.setEmail(dto.getEmail());
        usuario.setCreated_at(usuario.getCreated_at());

        if (dto.getFirst_name() != null && dto.getLast_name() != null && dto.getAge() != null && dto.getBirth_day() != null) {
            UserDetail userDetail = usuario.getUserDetail();
            if (userDetail != null){
                userDetail.setFirst_name(dto.getFirst_name());
                userDetail.setLast_name(dto.getLast_name());
                userDetail.setAge(dto.getAge());
                userDetail.setBirth_day(dto.getBirth_day());
                userDetailRepository.save(userDetail);
            }
        }


        if (dto.getRolesIds() != null){
            List<UserRol> userRoles = userRolRepository.findByUser(usuario);

            List<Integer> rolesIds = dto.getRolesIds();

            userRoles.stream()
                    .filter(userRol -> !rolesIds.contains(userRol.getRol().getId()))
                    .forEach(userRolRepository::delete);

            rolesIds.stream()
                    .filter(rolId -> userRoles.stream().noneMatch(userRol -> userRol.getRol().getId().equals(rolId)))
                    .forEach(rolId -> {
                        Rol rol = rolRepository.findById(rolId).orElse(null);
                        if (rol != null) {
                            UserRol userRol = new UserRol();
                            userRol.setUser(usuario);
                            userRol.setRol(rol);
                            userRol.setCreated_at(LocalDateTime.now());
                            userRol.setActive(true);

                            userRolRepository.save(userRol);
                        }
                    });
        }


        usuarioRepository.save(usuario);

        return usuarioMapper.toDto(usuario);
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> getUserById(Integer id) {
        return usuarioRepository.findById(id).map(usuarioMapper::toDtoDetailed);

    }

    @Override
    public void delete(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado " + id));

        if (usuario != null) {
            usuario.getRoles().clear();
        }
        usuarioRepository.deleteById(id);
    }

    /*ADICIONAL*/

    @Transactional
    public void deleteRolesByUser(Usuario usuario) {
        String jpql = "DELETE FROM UserRol ur WHERE ur.usuario = :user";
        entityManager.createQuery(jpql)
                .setParameter("user", usuario)
                .executeUpdate();
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
