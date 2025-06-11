package com.studyrats.application.dto;

import java.time.LocalDateTime;

public record UserResponseDTO(Long id, String name, String email, int points, LocalDateTime createdAt) {}

