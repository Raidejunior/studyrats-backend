package com.studyrats.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @EqualsAndHashCode.Include
    private final Long id;
    private String name;
    private String email;
    private String password;
    private int points;
    private final LocalDateTime createdAt;

    public User(Long id, String name, String email, String password, int points, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.points = points;
        this.createdAt = createdAt;
    }

    public static User create(String name, String email, String password) {
        return new User (
                null,
                name,
                email,
                password,
                0,
                LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)

        );
    }

    public void addPoints(int p) {
        this.points += p;
    }

    public boolean isHighScorer() {
        return points > 100;
    }
}