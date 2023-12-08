package com.snwm.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;
import com.snwm.objects.proxy.ProxyInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetProxyResponse extends ApiResponse {
    @SerializedName("list_count")
    private int listCount;

    @SerializedName("list")
    private Map<String, ProxyInfo> list;

    public List<ProxyInfo> getListValues() {
        return new ArrayList<>(list.values());
    }
}
