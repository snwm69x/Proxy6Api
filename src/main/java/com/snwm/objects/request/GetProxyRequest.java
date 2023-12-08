package com.snwm.objects.request;

import com.snwm.enums.ProxyState;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetProxyRequest {
    @Builder.Default
    private ProxyState state = ProxyState.ALL;
    private String descr;
    @Builder.Default
    private int page = 1;
    @Builder.Default
    private int limit = 1000;
}
