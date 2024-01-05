package com.system.wfms.Metrics;

import java.util.List;

public class CommandDataInfo {
    private boolean initial;
    private List<MetricEntry> ranges;

    public boolean isInitial() {
        return initial;
    }

    public List<MetricEntry> getRanges() {
        return ranges;
    }
}
