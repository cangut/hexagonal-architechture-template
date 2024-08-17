package com.architecture.can.aggregate;

import com.architecture.can.command.ProductCommand;
import com.architecture.can.entity.ProductJpaEntity;
import com.architecture.can.valueobject.Brand;
import com.architecture.can.valueobject.CategoryId;
import com.architecture.can.valueobject.ProductId;
import com.architecture.can.valueobject.ProductState;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Product {
    private ProductId productId;
    private CategoryId categoryId;
    private Brand brand;
    private ProductState productState;

    public Product(ProductJpaEntity entity) {
        this.productId = new ProductId(entity.getId());
        this.categoryId = new CategoryId(entity.getCategoryId());
        this.brand = new Brand(entity.getBrand());
        this.productState = entity.getProductState();
    }

    public Product(ProductCommand.Create command) {
        this.productId = new ProductId(UUID.randomUUID());
        this.categoryId = new CategoryId(command.categoryId());
        this.brand = new Brand(command.brand());
        this.productState = command.productState();
    }

    public void updateState(ProductCommand.UpdateState command) {
        this.productState = command.productState();
    }
}
