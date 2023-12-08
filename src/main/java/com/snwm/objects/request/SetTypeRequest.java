package com.snwm.objects.request;

import java.util.List;

import com.snwm.enums.ProxyType;

import lombok.Builder;
import lombok.Data;

@Data
public class SetTypeRequest {
    private ProxyType type;
    private List<Integer> ids;

    @Builder
    public SetTypeRequest(ProxyType type, List<Integer> ids) {
        if (type == null) {
            throw new IllegalArgumentException("ProxyType must be provided");
        }
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("IDs must be provided");
        }
        this.type = type;
        this.ids = ids;
    }
}
