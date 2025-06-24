package com.studyrats.component;

import com.studyrats.application.dto.CreateUserRequestDTO;
import com.studyrats.application.dto.UserResponseDTO;
import com.studyrats.application.service.UserApplicationService;
import com.studyrats.domain.model.User;
import com.studyrats.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;
import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = NONE)
public class UserComponentTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17")
            .withDatabaseName("studyrats")
            .withUsername("test")
            .withPassword("test");
    @Autowired
    private UserApplicationService userService;
    @Autowired
    private UserRepository userRepository;

    @DynamicPropertySource
    static void setup(DynamicPropertyRegistry reg) {
        reg.add("spring.datasource.url", postgres::getJdbcUrl);
        reg.add("spring.datasource.username", postgres::getUsername);
        reg.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeEach
    void cleanDataBase() {
        userRepository.deleteAll();
    }

    @Test
    void createUserComponentTest_shouldReturnCreatedAndPersist() {
        CreateUserRequestDTO dto = new CreateUserRequestDTO("Maísa", "maisa@example.com", "secret123");

        UserResponseDTO response = userService.create(dto);

        assertThat(response.name()).isEqualTo(dto.name());
        Optional<User> found = userRepository.findByEmail(dto.email());
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo(dto.name());
    }

}
