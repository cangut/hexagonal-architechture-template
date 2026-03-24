package com.architecture.can.aggregate;

import com.architecture.can.command.ProductCommand;
import com.architecture.can.entity.ProductJpaEntity;
import com.architecture.can.valueobject.Brand;
import com.architecture.can.valueobject.CategoryId;
import com.architecture.can.valueobject.ProductId;
import com.architecture.can.valueobject.ProductState;
import com.architecture.can.valueobject.SellerId;
import com.architecture.can.valueobject.Title;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Product {
    private ProductId productId;
    private CategoryId categoryId;
    private SellerId sellerId;
    private Brand brand;
    private Title title;
    private ProductState productState;

    public Product(ProductJpaEntity entity) {
        this.productId = new ProductId(entity.getId());
        this.categoryId = new CategoryId(entity.getCategoryId());
        this.sellerId = new SellerId(entity.getSellerId());
        this.brand = new Brand(entity.getBrand());
        this.title = new Title(entity.getTitle());
        this.productState = entity.getProductState();
    }

    public Product(ProductCommand.Create command) {
        this.productId = new ProductId(UUID.randomUUID());
        this.categoryId = new CategoryId(command.categoryId());
        this.sellerId = new SellerId(command.sellerId());
        this.brand = new Brand(command.brand());
        this.title = new Title(command.title());
        this.productState = command.productState();
    }

    public void updateState(ProductCommand.UpdateState command) {
        this.productState = command.productState();
    }
}
