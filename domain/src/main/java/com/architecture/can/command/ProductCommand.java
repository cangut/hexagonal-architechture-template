package com.architecture.can.command;

import com.architecture.can.valueobject.ProductState;
import lombok.Builder;

import java.util.UUID;

public interface ProductCommand {

    @Builder
    record Create(UUID categoryId, UUID sellerId, String brand, String title, ProductState productState) implements ProductCommand{}

    @Builder
    record UpdateState(ProductState productState) implements ProductCommand{};
}
