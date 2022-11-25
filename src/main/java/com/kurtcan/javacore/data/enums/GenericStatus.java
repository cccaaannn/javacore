package com.kurtcan.javacore.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenericStatus {
    PASSIVE(0),
    ACTIVE(1),
    DELETED(2);

    public final int status;
}
