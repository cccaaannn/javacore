package com.kurtcan.javacore.security.encryption.jasypt;

import com.kurtcan.javacore.security.encryption.abstracts.IPasswordEncryptor;
import org.jasypt.util.password.BasicPasswordEncryptor;

public class JasyptPasswordEncryptor implements IPasswordEncryptor {

    BasicPasswordEncryptor encryptor;

    public JasyptPasswordEncryptor() {
        this.encryptor = new BasicPasswordEncryptor();
    }

    public String encrypt(String rawPassword) {
        return encryptor.encryptPassword(rawPassword.toString());
    }

    public boolean matches(String rawPassword, String encryptedPassword) {
        return encryptor.checkPassword(rawPassword, encryptedPassword);
    }

}
