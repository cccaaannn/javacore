package com.kurtcan.javacore.utilities.email.abstracts;

import com.kurtcan.javacore.utilities.email.dtos.Email;
import com.kurtcan.javacore.utilities.email.exceptions.EmailClientException;

public interface IEmailClient {
    void send(Email email) throws EmailClientException;
}
