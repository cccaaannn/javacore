package com.kurtcan.javacore.utilities.email.javax.yandex;

import com.kurtcan.javacore.utilities.email.abstracts.IDynamicEmailClient;
import com.kurtcan.javacore.utilities.email.javax.JavaxDynamicEmailClient;

public class JavaxDynamicYandexClient extends JavaxDynamicEmailClient implements IDynamicEmailClient {
    public JavaxDynamicYandexClient() {
        super("smtp.yandex.ru", "465");
    }
}
