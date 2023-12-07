package com.snwm.enums;

public enum ProxyState {
    ACTIVE("active"),
    EXPIRED("expired"),
    EXPIRING("expiring"),
    ALL("all");

    private final String code;

    ProxyState(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
