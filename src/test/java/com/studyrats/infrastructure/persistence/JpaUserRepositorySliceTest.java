package com.studyrats.infrastructure.persistence;

import com.studyrats.domain.model.User;
import com.studyrats.infrastructure.persistence.mapper.UserEntityMapper;
import com.studyrats.infrastructure.persistence.repository.SpringDataUserRepository;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class JpaUserRepositorySliceTest {

    @Autowired
    private SpringDataUserRepository springRepo;
    private UserEntityMapper mapper = Mappers.getMapper(UserEntityMapper.class);

    @Test
    void saveAndFindByEmail_shouldPersistAndRetrieveUser() {
        JpaUserRepository repo = new JpaUserRepository(mapper, springRepo);
        User user = User.create("Maísa", "maisa@test.com", "password123");

        User saved = repo.save(user);
        assertThat(saved.getId()).isNotNull();

        Optional<User> fetched = repo.findByEmail("maisa@test.com");
        assertThat(fetched).isPresent()
                .hasValueSatisfying(u -> assertThat(u.getName()).isEqualTo("Maísa"));
    }

}
