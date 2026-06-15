package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.out.persistence.products;

import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

   /* @Lock(LockModeType.PESSIMISTIC_WRITE)
        @Query("""
            SELECT p
            FROM ProductEntity p
            WHERE p.id = :id
        """)
        Optional<ProductEntity> findByIdWithLock(UUID id);
    */

}

    

