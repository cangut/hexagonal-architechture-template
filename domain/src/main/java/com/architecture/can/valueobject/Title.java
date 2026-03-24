package com.architecture.can.valueobject;

public record Title(String value) {

    private static final int MAX_LENGTH = 100;

    public Title {
        validate(value);
    }

    private static void validate(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Title must not be blank");
        }
        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("Title must not exceed " + MAX_LENGTH + " characters");
        }
    }

    public boolean isValid() {
        try {
            validate(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean exceedsMaxLength() {
        return value != null && value.length() > MAX_LENGTH;
    }

    public boolean isBlank() {
        return value == null || value.isBlank();
    }
}
