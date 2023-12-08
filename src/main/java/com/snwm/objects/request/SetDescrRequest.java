package com.snwm.objects.request;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
public class SetDescrRequest {
    private String newDescr;
    private String oldDescr;
    private List<Integer> ids;

    @Builder
    public SetDescrRequest(String newDescr, String oldDescr, List<Integer> ids) {
        if (newDescr == null || newDescr.length() > 50) {
            throw new IllegalArgumentException("newDescr is required and cannot be longer than 50 characters");
        }
        if ((oldDescr != null) && (ids != null)) {
            throw new IllegalArgumentException("You can only set either oldDescr or IDs, not both");
        }
        this.newDescr = newDescr;
        this.oldDescr = oldDescr;
        this.ids = ids;
    }
}
