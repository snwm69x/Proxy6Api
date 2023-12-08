package com.snwm.objects.response;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeleteProxyResponse extends ApiResponse {
    @SerializedName("count")
    private int count;
}
