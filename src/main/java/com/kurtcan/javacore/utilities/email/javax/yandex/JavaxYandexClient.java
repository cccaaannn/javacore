package com.kurtcan.javacore.utilities.email.javax.yandex;

import com.kurtcan.javacore.utilities.email.abstracts.IEmailClient;
import com.kurtcan.javacore.utilities.email.javax.JavaxEmailClient;

public class JavaxYandexClient extends JavaxEmailClient implements IEmailClient {
    public JavaxYandexClient(String username, String password) {
        super("smtp.yandex.ru", "465", username, password);
    }
}
