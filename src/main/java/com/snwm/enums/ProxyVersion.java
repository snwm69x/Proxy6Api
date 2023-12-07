package com.snwm.enums;

public enum ProxyVersion {
    IPV4(4),
    IPV4_SHARED(3),
    IPV6(6);

    private final int code;

    ProxyVersion(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
