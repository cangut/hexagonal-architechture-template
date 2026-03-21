package com.architecture.can.response;

import com.architecture.can.aggregate.Product;
import com.architecture.can.valueobject.ProductState;
import lombok.Data;

import java.util.UUID;

@Data
public class ProductResponse {
    private UUID id;
    private UUID categoryId;
    private UUID sellerId;
    private String brand;
    private ProductState productState;

    public ProductResponse(Product product) {
        this.id = product.getProductId().getValue();
        this.categoryId = product.getCategoryId().getValue();
        this.sellerId = product.getSellerId().getValue();
        this.brand = product.getBrand().value();
        this.productState = product.getProductState();
    }
}
