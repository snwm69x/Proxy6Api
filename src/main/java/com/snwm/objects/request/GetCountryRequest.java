package com.snwm.objects.request;

import com.snwm.enums.ProxyVersion;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCountryRequest {
    @Builder.Default
    private ProxyVersion version = ProxyVersion.IPV6;
}
