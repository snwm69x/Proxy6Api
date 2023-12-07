package com.snwm.objects;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CheckResponse extends ApiResponse {
    @SerializedName("proxy_id")
    private int proxyId;

    @SerializedName("proxy_status")
    private boolean proxyStatus;
}
