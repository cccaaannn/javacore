package com.kurtcan.javacore.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PageOrder {
    asc("asc"),
    desc("desc");

    public final String ORDER_NAME;
}
