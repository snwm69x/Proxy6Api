package com.snwm.objects.response;

import java.util.Map;

import com.google.gson.annotations.SerializedName;
import com.snwm.objects.proxy.ProxyInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BuyProxyResponse extends ApiResponse {
    @SerializedName("count")
    private int count;

    @SerializedName("price")
    private double price;

    @SerializedName("period")
    private int period;

    @SerializedName("country")
    private String country;

    @SerializedName("list")
    private Map<String, ProxyInfo> list;
}
