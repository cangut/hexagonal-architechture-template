package com.architecture.can.ports.output;

import com.architecture.can.entity.ProductJpaEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductPostgresOutputPort {
    List<ProductJpaEntity> getProducts();
    Optional<ProductJpaEntity> getProduct(UUID id);
    UUID saveProduct(ProductJpaEntity product);
}
