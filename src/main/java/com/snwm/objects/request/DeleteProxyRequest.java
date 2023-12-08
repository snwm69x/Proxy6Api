package com.snwm.objects.request;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
public class DeleteProxyRequest {
    private String descr;
    private List<Integer> ids;

    @Builder
    public DeleteProxyRequest(String descr, List<Integer> ids) {
        if ((descr != null) && (ids != null)) {
            throw new IllegalArgumentException("You can only set either Descr or IDs, not both");
        }
        if ((descr == null) && (ids == null)) {
            throw new IllegalArgumentException("You must set either Descr or IDs");
        }
        this.descr = descr;
        this.ids = ids;
    }
}
