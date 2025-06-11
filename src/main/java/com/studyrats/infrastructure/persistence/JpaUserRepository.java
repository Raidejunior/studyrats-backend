package com.studyrats.infrastructure.persistence;

import com.studyrats.domain.model.User;
import com.studyrats.domain.repository.UserRepository;
import com.studyrats.infrastructure.persistence.entity.UserEntity;
import com.studyrats.infrastructure.persistence.mapper.UserEntityMapper;
import com.studyrats.infrastructure.persistence.repository.SpringDataUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaUserRepository implements UserRepository {

    private final UserEntityMapper mapper;
    private final SpringDataUserRepository springDataRepo;

    @Override
    public Optional<User> findByEmail(String email) {
        return springDataRepo.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public User save(User user) {
        UserEntity entity = mapper.toEntity(user);


        UserEntity saved = springDataRepo.save(entity);

        return mapper.toDomain(saved);
    }
}
