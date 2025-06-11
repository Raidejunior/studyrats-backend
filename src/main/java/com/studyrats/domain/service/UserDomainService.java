package com.studyrats.domain.service;

import com.studyrats.domain.exception.EmailAlreadyUsedException;
import com.studyrats.domain.model.User;
import com.studyrats.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDomainService {

    private final UserRepository userRepository;

    public User create (User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException(user.getEmail());
        }
        return userRepository.save(user);
    }

}
