package com.architecture.can.adapters.output.postgres;

import com.architecture.can.entity.ProductJpaEntity;
import com.architecture.can.ports.output.ProductPostgresOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductPostgresAdapter implements ProductPostgresOutputPort {
    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductJpaEntity> getProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductJpaEntity> getProduct(UUID id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional
    public UUID saveProduct(ProductJpaEntity product) {
        return productRepository.save(product).getId();
    }
}
