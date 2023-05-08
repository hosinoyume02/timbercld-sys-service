package com.timbercld.core.constant;

public enum CloudAgentEnum {
    /**
     * 七牛云
     */
    QINIU(1),
    /**
     * 阿里云
     */
    ALIYUN(2),
    /**
     * 腾讯云
     */
    QCLOUD(3),
    /**
     * FASTDFS
     */
    FASTDFS(4),
    /**
     * 本地
     */
    LOCAL(5);

    private int value;

    CloudAgentEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}