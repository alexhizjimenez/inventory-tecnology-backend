package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.config;

import com.alexhiz.hexagonal.inventory_tecnology.application.port.out.*;
import com.alexhiz.hexagonal.inventory_tecnology.application.services.*;
import com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.out.security.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class BeanConfig {

    @Bean
    public UserService userService(
            UserRepositoryPort userRepositoryPort,
            RoleRepositoryPort roleRepositoryPort,
            PasswordEncoderPort passwordEncoderPort) {
        return new UserService(userRepositoryPort, roleRepositoryPort, passwordEncoderPort);
    }

    @Bean
    public RoleService roleService(RoleRepositoryPort roleRepositoryPort) {
        return new RoleService(roleRepositoryPort);
    }

    @Bean
    public ProductService productService(ProductRepositoryPort productRepositoryPort) {
        return new ProductService(productRepositoryPort);
    }

    @Bean
    public CollaboratorService collaboratorService(CollaboratorRepositoryPort collaboratorRepositoryPort) {
        return new CollaboratorService(collaboratorRepositoryPort);
    }

    @Bean
    public AssignmentService assignmentService(
            AssignmentRepositoryPort assignmentRepositoryPort,
            ProductRepositoryPort productRepositoryPort,
            CollaboratorRepositoryPort collaboratorRepositoryPort,
            ProductEventPublisherPort eventPublisherPort,
            NotificationPort notificationPort) {
        return new AssignmentService(
                assignmentRepositoryPort,
                productRepositoryPort,
                collaboratorRepositoryPort,
                eventPublisherPort,
                notificationPort
        );
    }

    @Bean
    public AuthService authService(
            UserRepositoryPort userRepositoryPort,
            JwtService jwtService,
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService) {
        return new AuthService(userRepositoryPort, jwtService, authenticationManager, userDetailsService);
    }
}
