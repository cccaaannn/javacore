package com.kurtcan.javacore.utilities.email.javax.gmail;

import com.kurtcan.javacore.utilities.email.abstracts.IDynamicEmailClient;
import com.kurtcan.javacore.utilities.email.javax.JavaxDynamicEmailClient;

public class JavaxDynamicGmailClient extends JavaxDynamicEmailClient implements IDynamicEmailClient {
    public JavaxDynamicGmailClient() {
        super("smtp.gmail.com", "465");
    }
}
