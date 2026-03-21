package com.architecture.can.valueobject;

import java.util.UUID;

public class SellerId extends BaseId<UUID> {
    public SellerId(UUID value) {
        super(value);
    }
}
