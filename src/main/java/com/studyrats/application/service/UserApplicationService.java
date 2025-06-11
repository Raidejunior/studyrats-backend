package com.studyrats.application.service;

import com.studyrats.api.mapper.UserMapper;
import com.studyrats.application.dto.CreateUserRequestDTO;
import com.studyrats.application.dto.UserResponseDTO;
import com.studyrats.domain.model.User;
import com.studyrats.domain.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserApplicationService {

    private final UserDomainService userDomainService;
    private final UserMapper mapper;

    @Transactional
    public UserResponseDTO create(CreateUserRequestDTO dto) {
        User user = mapper.toDomain(dto);

        User createdUser = userDomainService.create(user);

        return mapper.toResponseDTO(createdUser);
    }

}
