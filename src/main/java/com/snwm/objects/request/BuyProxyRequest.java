package com.snwm.objects.request;

import com.snwm.enums.Country;
import com.snwm.enums.ProxyType;
import com.snwm.enums.ProxyVersion;

import lombok.Builder;
import lombok.Data;

@Data
public class BuyProxyRequest {
    private Integer count;
    private Integer period;
    private Country country;
    private ProxyVersion version;
    private ProxyType type;
    private String descr;
    private Boolean autoProlong;

    @Builder
    public BuyProxyRequest(int count, int period, Country country, ProxyVersion version, ProxyType type, String descr,
            Boolean autoProlong) {
        if (count <= 0 || period <= 0 || country == null) {
            throw new IllegalArgumentException("Count, Period and Country must be provided");
        }
        this.count = count;
        this.period = period;
        this.country = country;
        this.version = version != null ? version : ProxyVersion.IPV6;
        this.type = type != null ? type : ProxyType.HTTPS;
        this.descr = descr;
        this.autoProlong = autoProlong;
    }
}
