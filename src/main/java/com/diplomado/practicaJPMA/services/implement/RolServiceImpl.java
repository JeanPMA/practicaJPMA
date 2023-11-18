package com.diplomado.practicaJPMA.services.implement;

import com.diplomado.practicaJPMA.domain.entities.Rol;
import com.diplomado.practicaJPMA.dto.RolDTO;
import com.diplomado.practicaJPMA.repositories.RolRepository;
import com.diplomado.practicaJPMA.services.RolService;
import com.diplomado.practicaJPMA.services.mapper.RolMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;
    private final RolMapper rolMapper;

    public RolServiceImpl(RolRepository rolRepository, RolMapper rolMapper) {
        this.rolRepository = rolRepository;
        this.rolMapper = rolMapper;
    }

    @Override
    public List<RolDTO> listRoles() {
        return rolRepository.findAll()
                .stream()
                .map(rolMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public RolDTO save(RolDTO dto) {
        Rol rol = rolRepository.save(rolMapper.toEntity(dto));
        return rolMapper.toDto(rol);
    }

    @Override
    public Optional<RolDTO> getRolById(Integer id) {
        return rolRepository.findById(id).map(rolMapper::toDto);
    }

    @Override
    public void delete(Integer id) {
        rolRepository.deleteById(id);
    }
}
