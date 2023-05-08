package com.timbercld.core.constant;

public enum SchedulerStatusEnum {
    /**
     * 暂停
     */
    PAUSE(0),
    /**
     * 正常
     */
    NORMAL(1);

    private int value;

    SchedulerStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
