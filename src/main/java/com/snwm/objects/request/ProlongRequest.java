package com.snwm.objects.request;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
public class ProlongRequest {
    private int period;
    private List<Integer> ids;

    @Builder
    public ProlongRequest(int period, List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("IDs must be provided");
        }
        if (period <= 0) {
            throw new IllegalArgumentException("Period must be greater than 0");
        }
        this.period = period;
        this.ids = ids;
    }
}
