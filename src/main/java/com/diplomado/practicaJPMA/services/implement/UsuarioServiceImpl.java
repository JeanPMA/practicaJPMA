package com.diplomado.practicaJPMA.services.implement;

import com.diplomado.practicaJPMA.domain.entities.UserDetail;
import com.diplomado.practicaJPMA.domain.entities.UserRol;
import com.diplomado.practicaJPMA.domain.entities.Usuario;
import com.diplomado.practicaJPMA.dto.UsuarioDTO;
import com.diplomado.practicaJPMA.repositories.UserDetailRepository;
import com.diplomado.practicaJPMA.repositories.UserRolRepository;
import com.diplomado.practicaJPMA.repositories.UsuarioRepository;
import com.diplomado.practicaJPMA.services.UsuarioService;
import com.diplomado.practicaJPMA.services.mapper.UsuarioMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final UserDetailRepository userDetailRepository;

    private final UserRolRepository userRolRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, UserDetailRepository userDetailRepository, UserRolRepository userRolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.userDetailRepository = userDetailRepository;
        this.userRolRepository = userRolRepository;
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
        Usuario usuario = usuarioRepository.save(usuarioMapper.toEntity(dto));
        userDetailRepository.save(new UserDetail(dto.getFirst_name(), dto.getLast_name(), dto.getAge(), dto.getBirth_day(),usuario));

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
}
