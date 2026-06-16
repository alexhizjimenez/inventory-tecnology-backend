package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.out.persistence.users;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.User;
import com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.out.persistence.roles.RolePersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPersistenceMapper {

    private final RolePersistenceMapper roleMapper;

    public User toDomain(UserEntity entity) {
        if (entity == null) return null;
        return User.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .roles(roleMapper.toDomainSet(entity.getRoles()))
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public UserEntity toEntity(User domain) {
        if (domain == null) return null;
        return UserEntity.builder()
                .id(domain.getId())
                .fullName(domain.getFullName())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .roles(roleMapper.toEntitySet(domain.getRoles()))
                .createdAt(domain.getCreatedAt())
                .build();
    }

}
