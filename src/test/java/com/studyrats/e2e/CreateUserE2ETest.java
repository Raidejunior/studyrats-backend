package com.studyrats.e2e;

import com.studyrats.application.dto.CreateUserRequestDTO;
import com.studyrats.application.dto.UserResponseDTO;
import com.studyrats.domain.model.User;
import com.studyrats.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.net.URI;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateUserE2ETest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17")
            .withDatabaseName("studyrats")
            .withUsername("test")
            .withPassword("test");

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @LocalServerPort
    private int port;

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
    void createUserE2E_shouldReturn201AndPersist() {
        CreateUserRequestDTO requestDTO = new CreateUserRequestDTO("Maísa", "maisa@example.com", "secret123");
        ResponseEntity<UserResponseDTO> response = restTemplate.postForEntity(
                URI.create("http://localhost:" + port + "/users"),
                requestDTO,
                UserResponseDTO.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Optional<User> user = userRepository.findByEmail(requestDTO.email());
        assertThat(user).isPresent();
    }
}
