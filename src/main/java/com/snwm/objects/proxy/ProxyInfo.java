package com.snwm.objects.proxy;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class ProxyInfo {
    @SerializedName("id")
    private String id;

    @SerializedName("ip")
    private String ip;

    @SerializedName("host")
    private String host;

    @SerializedName("port")
    private String port;

    @SerializedName("user")
    private String user;

    @SerializedName("pass")
    private String pass;

    @SerializedName("type")
    private String type;

    @SerializedName("country")
    private String country;

    @SerializedName("date")
    private String date;

    @SerializedName("date_end")
    private String dateEnd;

    @SerializedName("unixtime")
    private long unixtime;

    @SerializedName("unixtime_end")
    private long unixtimeEnd;

    @SerializedName("descr")
    private String descr;

    @SerializedName("active")
    private String active;
}
