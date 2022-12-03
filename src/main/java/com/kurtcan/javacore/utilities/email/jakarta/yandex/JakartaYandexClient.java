package com.kurtcan.javacore.utilities.email.jakarta.yandex;

import com.kurtcan.javacore.utilities.email.abstracts.IEmailClient;
import com.kurtcan.javacore.utilities.email.jakarta.JakartaEmailClient;

public class JakartaYandexClient extends JakartaEmailClient implements IEmailClient {
    public JakartaYandexClient(String username, String password) {
        super("smtp.yandex.ru", "465", username, password);
    }
}
