package com.studyrats.infrastructure.persistence.mapper;

import com.studyrats.domain.model.User;
import com.studyrats.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    UserEntity toEntity(User domain);

    User toDomain(UserEntity entity);
}
