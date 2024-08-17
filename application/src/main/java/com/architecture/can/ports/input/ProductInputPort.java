package com.architecture.can.ports.input;

import com.architecture.can.aggregate.Product;
import com.architecture.can.command.ProductCommand;
import com.architecture.can.entity.ProductJpaEntity;
import com.architecture.can.ports.output.ProductPostgresOutputPort;
import com.architecture.can.query.ProductQuery;
import com.architecture.can.response.ProductResponse;
import com.architecture.can.usecase.ProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductInputPort implements ProductUseCase {

    private final ProductPostgresOutputPort postgresOutputPort;

    @Override
    public List<ProductResponse> getProducts() {
        List<ProductJpaEntity> productJpaEntities = postgresOutputPort.getProducts();
        return productJpaEntities.stream()
                .map(Product::new)
                .map(ProductResponse::new)
                .toList();
    }

    @Override
    public ProductResponse getById(ProductQuery.ByProductId query) {
        return postgresOutputPort.getProduct(query.productId())
                .map(Product::new)
                .map(ProductResponse::new)
                .orElseThrow(() -> new RuntimeException("Entity does not found"));
    }

    @Override
    public UUID save(ProductCommand.Create command) {
        Product product = new Product(command);
        ProductJpaEntity productJpaEntity = new ProductJpaEntity(product);
        return postgresOutputPort.saveProduct(productJpaEntity);
    }

    @Override
    public UUID updateState(ProductCommand.UpdateState command, UUID productId) {
        Product product = postgresOutputPort.getProduct(productId)
                .map(Product::new)
                .orElseThrow(() -> new RuntimeException("Entity does not found"));
        product.updateState(command);
        return postgresOutputPort.saveProduct(new ProductJpaEntity(product));
    }


}
