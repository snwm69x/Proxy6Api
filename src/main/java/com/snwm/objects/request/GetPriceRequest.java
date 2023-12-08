package com.snwm.objects.request;

import com.snwm.enums.ProxyVersion;

import lombok.Builder;
import lombok.Data;

@Data
public class GetPriceRequest {
    private Integer count;
    private Integer period;
    private ProxyVersion version;

    @Builder
    public GetPriceRequest(Integer count, Integer period, ProxyVersion version) {
        if (count == null || count <= 0) {
            throw new IllegalArgumentException("Count must be provided and greater than 0");
        }
        if (period == null || period <= 0) {
            throw new IllegalArgumentException("Period must be provided and greater than 0");
        }
        this.count = count;
        this.period = period;
        this.version = version != null ? version : ProxyVersion.IPV6;
    }
}
