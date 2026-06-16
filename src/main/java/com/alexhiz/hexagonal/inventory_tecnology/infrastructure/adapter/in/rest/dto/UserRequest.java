package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest.dto;

import lombok.*;

import java.util.Set;
import java.util.UUID;


@Getter  @Setter
public class UserRequest {
    private String fullName;
    private String email;
    private String password;
    private Set<UUID> roleIds;
}
