package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.config;

import com.alexhiz.hexagonal.inventory_tecnology.application.port.out.PasswordEncoderPort;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.out.RoleRepositoryPort;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.out.UserRepositoryPort;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Role;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.RolesUser;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class DatabaseSeeder implements ApplicationRunner{
    private final UserRepositoryPort userRepositoryPort;
    private final RoleRepositoryPort roleRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Iniciando la siembra de datos (Seeding)...");
        Role admin = roleRepositoryPort.findByName(RolesUser.ADMIN).orElseGet(() -> {
            log.info("Seeder: Creando rol ADMIN...");
            return roleRepositoryPort.save(Role.builder().name(RolesUser.ADMIN).build());
        });

        String email = "admin@gmail.com";
        if(userRepositoryPort.findByEmail(email).isEmpty()){
            log.info("Seeder: Creando rol USER...");
            User adminUser = User.builder()
                    .fullName("admin")
                    .email(email)
                    .password(passwordEncoderPort.encode("admin"))
                    .roles(Set.of(admin))
                    .createdAt(LocalDateTime.now())
                    .build();
            userRepositoryPort.save(adminUser);
            log.info("Seeder: Usuario administrador creado con éxito.");
        }
    }
}
