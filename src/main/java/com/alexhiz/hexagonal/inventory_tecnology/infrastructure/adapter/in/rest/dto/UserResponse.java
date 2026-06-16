package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest.dto;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Role;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Builder
public class UserResponse {
    private UUID id;
    private String fullName;
    private String email;
    private String password;
    private Set<Role> roles;
    private LocalDateTime createdAt;

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
