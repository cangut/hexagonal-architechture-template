package com.architecture.can.valueobject;

public record Brand(String value) {
    @Override
    public String value() {
        return value;
    }
}
