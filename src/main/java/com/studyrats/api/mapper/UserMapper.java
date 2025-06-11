package com.studyrats.api.mapper;

import com.studyrats.application.dto.CreateUserRequestDTO;
import com.studyrats.application.dto.UserResponseDTO;
import com.studyrats.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    default User toDomain(CreateUserRequestDTO dto) {
        return User.create(dto.name(), dto.email(), dto.password());
    }

    UserResponseDTO toResponseDTO(User user);

}
