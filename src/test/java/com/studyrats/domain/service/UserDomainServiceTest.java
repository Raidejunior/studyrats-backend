package com.studyrats.domain.service;

import com.studyrats.domain.exception.EmailAlreadyUsedException;
import com.studyrats.domain.model.User;
import com.studyrats.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDomainServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDomainService service;

    @Test
    void create_withUniqueEmail_succeeds() {
        User user = User.create("Maísa", "maisa@example.com", "password123");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);

        User result = service.create(user);
        assertSame(user, result);
        verify(userRepository).save(user);
    }

    @Test
    void create_withDuplicateEmail_throwsException() {
        User user = User.create("Raide", "raide@example.com", "password123");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        assertThrows(EmailAlreadyUsedException.class, () -> service.create(user));
        verify(userRepository, never()).save(any());
    }

}
