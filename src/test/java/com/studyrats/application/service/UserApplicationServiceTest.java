package com.studyrats.application.service;

import com.studyrats.api.mapper.UserMapper;
import com.studyrats.application.dto.CreateUserRequestDTO;
import com.studyrats.application.dto.UserResponseDTO;
import com.studyrats.domain.model.User;
import com.studyrats.domain.service.UserDomainService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserApplicationServiceTest {

    @Mock
    private UserDomainService domainService;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserApplicationService appService;

    @Test
    void create_delegatesToDomain_andMapsResponse() {
        CreateUserRequestDTO requestDTO = new CreateUserRequestDTO("Maísa", "maisa@example.com", "secret");
        User domainUser = User.create("Maísa", "maisa@example.com", "secret");
        User savedUser = new User(1L, "Maísa", "maisa@example.com", "secret", 0, domainUser.getCreatedAt());
        UserResponseDTO responseDTO = new UserResponseDTO(1L, "Maísa", "maisa@example.com", 0, domainUser.getCreatedAt());

        when(mapper.toDomain(requestDTO)).thenReturn(domainUser);
        when(domainService.create(domainUser)).thenReturn(savedUser);
        when(mapper.toResponseDTO(savedUser)).thenReturn(responseDTO);

        UserResponseDTO result = appService.create(requestDTO);
        assertEquals(responseDTO, result);

        InOrder inOrder = inOrder(mapper, domainService, mapper);
        inOrder.verify(mapper).toDomain(requestDTO);
        inOrder.verify(domainService).create(domainUser);
        inOrder.verify(mapper).toResponseDTO(savedUser);
    }


}
