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
import jakarta.transaction.Transactional;
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
    public List<UsuarioDTO> listUsers() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDto).collect(Collectors.toList());
    }

    @Override
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

        UserDetail userDetail = new UserDetail();
        userDetail.setUser(usuario);
        userDetail.setFirst_name(dto.getFirst_name());
        userDetail.setLast_name(dto.getLast_name());
        userDetail.setAge(dto.getAge());
        userDetail.setBirth_day(dto.getBirth_day());
        userDetailRepository.save(userDetail);

        List<Rol> roles = rolRepository.findAllById(dto.getRolesIds());

        for (Rol rol : roles) {
            UserRol userRol = new UserRol();
            userRol.setUser(usuario);
            userRol.setRol(rol);
            userRol.setCreated_at(LocalDateTime.now());
            userRol.setActive(true);

            userRolRepository.save(userRol);
        }

        return usuarioMapper.toDto(usuario);
    }
    public UsuarioDTO editarUsuario(Integer usuarioId, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setUsername(dto.getUsername());
        usuario.setPassword(dto.getPassword());
        usuario.setEmail(dto.getEmail());
        usuario.setCreated_at(usuario.getCreated_at());

        UserDetail userDetail = usuario.getUserDetail();
        if (userDetail != null){
            userDetail.setFirst_name(dto.getFirst_name());
            userDetail.setLast_name(dto.getLast_name());
            userDetail.setAge(dto.getAge());
            userDetail.setBirth_day(dto.getBirth_day());
            userDetailRepository.save(userDetail);
        }

        // Obtener roles actuales del usuario
        List<UserRol> userRoles = userRolRepository.findByUser(usuario);

// Obtener la lista de IDs de roles proporcionados en el DTO
        List<Integer> rolesIds = dto.getRolesIds();

// Eliminar roles que ya no están en la lista proporcionada
        userRoles.stream()
                .filter(userRol -> !rolesIds.contains(userRol.getRol().getId()))
                .forEach(userRolRepository::delete);

// Agregar nuevos roles que no existen actualmente para el usuario
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

        usuarioRepository.save(usuario);

        return usuarioMapper.toDto(usuario);
    }
    @Override
    public Optional<UsuarioDTO> getUserById(Integer id) {
        return usuarioRepository.findById(id).map(usuarioMapper::toDtoDetailed);
    }

    @Override
    public void delete(Integer id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario parcial(Usuario usuario, Integer id){
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        BeanUtils.copyProperties(usuario, usuarioExistente, "id");

        return usuarioRepository.save(usuarioExistente);
    }

    @Transactional
    public void deleteRolesByUser(Usuario usuario) {
        String jpql = "DELETE FROM UserRol ur WHERE ur.usuario = :user";
        entityManager.createQuery(jpql)
                .setParameter("user", usuario)
                .executeUpdate();
    }
}
