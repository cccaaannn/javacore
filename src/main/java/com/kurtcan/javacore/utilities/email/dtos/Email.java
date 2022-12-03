package com.kurtcan.javacore.utilities.email.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Email {

    public Email(String subject, String body, List<String> to) {
        this.subject = subject;
        this.body = body;
        this.to = to;
        List<String> cc = new ArrayList<>();
        List<String> bcc = new ArrayList<>();
        List<File> attachments = new ArrayList<>();
    }

    public Email(String subject, String body, String to) {
        this.subject = subject;
        this.body = body;
        this.to = List.of(to);
        List<String> cc = new ArrayList<>();
        List<String> bcc = new ArrayList<>();
        List<File> attachments = new ArrayList<>();
    }

    public static class EmailBuilder {
        public EmailBuilder to(String to) {
            this.to = List.of(to);
            return this;
        }
        public EmailBuilder to(List<String> to) {
            this.to = to;
            return this;
        }
    }

    @Builder.Default
    private String subject = "";

    @Builder.Default
    private String body = "";

    private List<String> to;

    @Builder.Default
    private List<String> cc = new ArrayList<>();

    @Builder.Default
    private List<String> bcc = new ArrayList<>();

    @Builder.Default
    private List<File> attachments = new ArrayList<>();

}
