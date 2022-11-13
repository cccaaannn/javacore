package com.kurtcan.javacore.data.enums;

public enum OnlineStatus {
    OFFLINE(0),
    ONLINE(1);

    public final int status;
    OnlineStatus(int status) {
        this.status = status;
    }
}
