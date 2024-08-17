package com.architecture.can.valueobject;

import java.util.UUID;

public class CategoryId extends BaseId<UUID> {
    public CategoryId(UUID value) {
        super(value);
    }
}
