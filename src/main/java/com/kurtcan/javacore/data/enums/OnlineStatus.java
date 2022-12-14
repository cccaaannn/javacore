package com.kurtcan.javacore.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OnlineStatus {
    OFFLINE(0),
    ONLINE(1);

    public final int status;
}
