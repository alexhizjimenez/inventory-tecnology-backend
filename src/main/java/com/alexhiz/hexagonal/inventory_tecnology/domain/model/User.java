package com.alexhiz.hexagonal.inventory_tecnology.domain.model;

import com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest.dto.UserResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @EqualsAndHashCode.Include
    private UUID id;
    private String fullName;
    private String email;
    private String password;
    private Set<Role> roles;
    private LocalDateTime createdAt;

}
