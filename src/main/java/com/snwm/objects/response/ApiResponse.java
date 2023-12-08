package com.snwm.objects.response;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class ApiResponse {
    @SerializedName("status")
    private String status;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("balance")
    private String balance;

    @SerializedName("currency")
    private String currency;
}
