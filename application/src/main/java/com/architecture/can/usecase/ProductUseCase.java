package com.architecture.can.usecase;

import com.architecture.can.command.ProductCommand;
import com.architecture.can.query.ProductQuery;
import com.architecture.can.response.ProductResponse;

import java.util.List;
import java.util.UUID;

public interface ProductUseCase {
    List<ProductResponse> getProducts();
    ProductResponse getById(ProductQuery.ByProductId query);
    UUID save(ProductCommand.Create command);
    UUID updateState(ProductCommand.UpdateState command, UUID productId);
}
