package com.snwm.objects.request;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IpAuthRequest {
    private List<String> ipOrDelete;
}
