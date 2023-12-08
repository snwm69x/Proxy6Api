package com.snwm;

import com.snwm.api.Proxy6NetApi;
import com.snwm.enums.Country;
import com.snwm.enums.ProxyType;
import com.snwm.enums.ProxyVersion;
import com.snwm.objects.request.BuyProxyRequest;

public class App {
    public static void main(String[] args) {
        Proxy6NetApi api = Proxy6NetApi.createWithApiKey("your-api-key");
        api.buyProxy(BuyProxyRequest.builder()
                .count(30)
                .period(30)
                .country(Country.RUSSIA)
                .version(ProxyVersion.IPV4_SHARED)
                .type(ProxyType.SOCKS5)
                .descr("test")
                .autoProlong(true)
                .build());
    }
}
