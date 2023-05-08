package com.timbercld.core.constant;

public enum SmsAgentEnum {
    /**
     * 阿里云
     */
    ALIYUN(1),
    /**
     * 腾讯云
     */
    QCLOUD(2);

    private int value;

    SmsAgentEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}