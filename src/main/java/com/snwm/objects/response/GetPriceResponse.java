package com.snwm.objects.response;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetPriceResponse extends ApiResponse {
    @SerializedName("price")
    private double price;

    @SerializedName("price_single")
    private double priceSingle;

    @SerializedName("period")
    private int period;

    @SerializedName("count")
    private int count;
}
