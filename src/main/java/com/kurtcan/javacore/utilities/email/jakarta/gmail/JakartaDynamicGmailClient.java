package com.kurtcan.javacore.utilities.email.jakarta.gmail;

import com.kurtcan.javacore.utilities.email.abstracts.IDynamicEmailClient;
import com.kurtcan.javacore.utilities.email.jakarta.JakartaDynamicEmailClient;

public class JakartaDynamicGmailClient extends JakartaDynamicEmailClient implements IDynamicEmailClient {
    public JakartaDynamicGmailClient() {
        super("smtp.gmail.com", "465");
    }
}
