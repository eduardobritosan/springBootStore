package com.eduardo.store.enums;

public enum ProductStateEnum {
    ACTIVE("Active")
    ,DISCONTINUED("Discontinued");

    private final String value;

    ProductStateEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
