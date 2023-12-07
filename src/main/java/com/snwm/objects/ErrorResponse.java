package com.snwm.objects;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class ErrorResponse {
    @SerializedName("status")
    private String status;

    @SerializedName("error_id")
    private int errorId;

    @SerializedName("error")
    private String error;
}
