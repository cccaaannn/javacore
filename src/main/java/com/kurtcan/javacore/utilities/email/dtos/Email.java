package com.kurtcan.javacore.utilities.email.dtos;

import lombok.Builder;
import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Email {

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
