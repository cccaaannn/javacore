package com.kurtcan.javacore.utilities.email.jakarta;


import com.kurtcan.javacore.utilities.email.abstracts.IDynamicEmailClient;
import com.kurtcan.javacore.utilities.email.dtos.Email;
import com.kurtcan.javacore.utilities.email.exceptions.EmailClientException;

import java.util.Properties;

public class JakartaDynamicEmailClient extends BaseEmailClient implements IDynamicEmailClient {

    public JakartaDynamicEmailClient(Properties properties) {
        super(properties);
    }

    public JakartaDynamicEmailClient(String host, String port) {
        super();

        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
    }

    @Override
    public void send(String username, String password, Email email) throws EmailClientException {
        super.send(username, password, email);
    }

}
