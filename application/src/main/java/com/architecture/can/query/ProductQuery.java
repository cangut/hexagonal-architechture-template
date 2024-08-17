package com.architecture.can.query;

import lombok.Builder;

import java.util.UUID;

public interface ProductQuery {
    @Builder
    record ByProductId(UUID productId) implements ProductQuery {}
}
