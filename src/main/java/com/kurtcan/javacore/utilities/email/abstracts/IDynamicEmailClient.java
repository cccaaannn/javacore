package com.kurtcan.javacore.utilities.email.abstracts;

import com.kurtcan.javacore.utilities.email.dtos.Email;
import com.kurtcan.javacore.utilities.email.exceptions.EmailClientException;

public interface IDynamicEmailClient {
    void send(String username, String password, Email email) throws EmailClientException;
}
