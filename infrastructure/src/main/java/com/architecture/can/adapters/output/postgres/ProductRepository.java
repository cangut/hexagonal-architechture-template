package com.architecture.can.adapters.output.postgres;

import com.architecture.can.entity.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface ProductRepository extends JpaRepository<ProductJpaEntity, UUID> {
}
