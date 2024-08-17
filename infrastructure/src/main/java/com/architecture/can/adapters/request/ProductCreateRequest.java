package com.architecture.can.adapters.request;

import com.architecture.can.valueobject.ProductState;
import lombok.Data;

import java.util.UUID;

@Data
public class ProductCreateRequest {
    private UUID categoryId;
    private String brand;
    private ProductState productState;
}
