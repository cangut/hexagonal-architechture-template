package com.architecture.can.adapters.input.rest;

import com.architecture.can.adapters.request.ProductCreateRequest;
import com.architecture.can.adapters.request.ProductStateUpdateRequest;
import com.architecture.can.command.ProductCommand;
import com.architecture.can.query.ProductQuery;
import com.architecture.can.response.ProductResponse;
import com.architecture.can.usecase.ProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/products")
public class ProductRestAdapter {
    private final ProductUseCase productUseCase;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return new ResponseEntity<>(productUseCase.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable UUID id) {
        ProductQuery.ByProductId byProductId = ProductQuery.ByProductId.builder()
                .productId(id)
                .build();
        ProductResponse response = productUseCase.getById(byProductId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UUID> save(@RequestBody ProductCreateRequest request) {
        ProductCommand.Create createCommand = ProductCommand.Create.builder()
                .categoryId(request.getCategoryId())
                .brand(request.getBrand())
                .productState(request.getProductState())
                .build();
        return new ResponseEntity<>(productUseCase.save(createCommand), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UUID> updateStatus(@PathVariable UUID id, @RequestBody ProductStateUpdateRequest updateRequest) {
        ProductCommand.UpdateState updateStateCommand = ProductCommand.UpdateState.builder()
                .productState(updateRequest.getProductState())
                .build();
        return new ResponseEntity<>(productUseCase.updateState(updateStateCommand, id), HttpStatus.OK);
    }
}
