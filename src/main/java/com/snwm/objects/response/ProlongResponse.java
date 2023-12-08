package com.snwm.objects.response;

import java.util.Map;

import com.google.gson.annotations.SerializedName;
import com.snwm.objects.proxy.ProlongedProxyInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProlongResponse extends ApiResponse {
    @SerializedName("price")
    private double price;

    @SerializedName("period")
    private int period;

    @SerializedName("count")
    private int count;

    @SerializedName("list")
    private Map<String, ProlongedProxyInfo> list;

}
