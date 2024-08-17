package com.architecture.can.adapters.request;

import com.architecture.can.valueobject.ProductState;
import lombok.Data;

@Data
public class ProductStateUpdateRequest {
    private ProductState productState;
}
