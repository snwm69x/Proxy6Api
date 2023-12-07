package com.snwm.enums;

public enum ProxyType {
    HTTPS("http"),
    SOCKS5("socks");

    private final String code;

    ProxyType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
