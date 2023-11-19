package com.diplomado.practicaJPMA.services.implement;

import com.diplomado.practicaJPMA.dto.RolDTO;
import com.diplomado.practicaJPMA.dto.UserDetailDTO;
import com.diplomado.practicaJPMA.repositories.UserDetailRepository;
import com.diplomado.practicaJPMA.services.UserDetailService;
import com.diplomado.practicaJPMA.services.mapper.UserDetailMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserDetailServiceImpl implements UserDetailService {

    private final UserDetailRepository userDetailRepository;
    private final UserDetailMapper userDetailMapper;

    public UserDetailServiceImpl(UserDetailRepository userDetailRepository, UserDetailMapper userDetailMapper) {
        this.userDetailRepository = userDetailRepository;
        this.userDetailMapper = userDetailMapper;
    }
    @Override
    public List<UserDetailDTO> listUserDetails() {
        return userDetailRepository.findAll()
                .stream()
                .map(userDetailMapper::toDto).collect(Collectors.toList());
    }
}
