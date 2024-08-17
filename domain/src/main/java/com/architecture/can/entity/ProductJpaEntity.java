package com.architecture.can.entity;

import com.architecture.can.aggregate.Product;
import com.architecture.can.valueobject.ProductState;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor
public class ProductJpaEntity {
    @Id
    private UUID id;
    private UUID categoryId;
    private String brand;
    private ProductState productState;

    public ProductJpaEntity(Product product) {
        this.id = product.getProductId().getValue();
        this.categoryId = product.getCategoryId().getValue();
        this.brand = product.getBrand().value();
        this.productState = product.getProductState();
    }

}
