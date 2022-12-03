package com.kurtcan.javacore.utilities.email.jakarta.gmail;

import com.kurtcan.javacore.utilities.email.abstracts.IEmailClient;
import com.kurtcan.javacore.utilities.email.jakarta.JakartaEmailClient;

public class JakartaGmailClient extends JakartaEmailClient implements IEmailClient {
    public JakartaGmailClient(String username, String password) {
        super("smtp.gmail.com", "465", username, password);
    }
}
