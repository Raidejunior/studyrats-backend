package com.studyrats.api.controller;

import com.studyrats.application.dto.CreateUserRequestDTO;
import com.studyrats.application.dto.UserResponseDTO;
import com.studyrats.application.service.UserApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserApplicationService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid CreateUserRequestDTO dto, UriComponentsBuilder uriBuilder) {

        UserResponseDTO userResponse = userService.create(dto);

        var uri = uriBuilder.path("/users/{id}").buildAndExpand(userResponse.id()).toUri();

        return ResponseEntity.created(uri).body(userResponse);
    }
}
