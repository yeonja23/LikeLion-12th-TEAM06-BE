package com.likelion.plantication.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.likelion.plantication.challenge.domain.Challenge;
import com.likelion.plantication.global.exception.CustomException;
import com.likelion.plantication.global.exception.code.ErrorCode;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "EMAIL", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "NAME", length = 20, nullable = false)
    private String name;

    @Column(name = "NICKNAME", length = 20, nullable = false)
    private String nickname;

    @Column(name = "USER_IMAGE")
    private String profileImage;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "MODIFIED_AT")
    private LocalDateTime modifiedAt;

    @Column(name = "ROLE", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Challenge> challenges = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @Builder
    public User(Long userId, String email, String password, String phone, String name, String nickname, String profileImage, LocalDateTime createdAt, LocalDateTime modifiedAt, Role role) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.name = name;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.role = role;
    }
}
