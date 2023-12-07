package com.snwm.objects;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeleteResponse extends ApiResponse {
    @SerializedName("count")
    private int count;
}
