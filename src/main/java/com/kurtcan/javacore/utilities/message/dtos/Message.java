package com.kurtcan.javacore.utilities.message.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Message {

    @Builder.Default
    private String body = "";

    private List<String> to;

}
