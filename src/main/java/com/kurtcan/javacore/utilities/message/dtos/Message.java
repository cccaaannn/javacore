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

    public static class MessageBuilder {
        public MessageBuilder to(String to) {
            this.to = List.of(to);
            return this;
        }

        public MessageBuilder to(List<String> to) {
            this.to = to;
            return this;
        }
    }

}
