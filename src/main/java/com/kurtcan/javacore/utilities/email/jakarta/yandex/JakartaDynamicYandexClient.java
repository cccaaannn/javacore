package com.kurtcan.javacore.utilities.email.jakarta.yandex;

import com.kurtcan.javacore.utilities.email.abstracts.IDynamicEmailClient;
import com.kurtcan.javacore.utilities.email.jakarta.JakartaDynamicEmailClient;

public class JakartaDynamicYandexClient extends JakartaDynamicEmailClient implements IDynamicEmailClient {
    public JakartaDynamicYandexClient() {
        super("smtp.yandex.ru", "465");
    }
}
