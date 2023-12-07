package com.snwm.objects.proxy;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProlongedProxyInfo {
    @SerializedName("id")
    private int id;

    @SerializedName("date_end")
    private String dateEnd;

    @SerializedName("unixtime_end")
    private long unixtimeEnd;
}
