package com.kurtcan.javacore.security.encryption.abstracts;

public interface IPasswordEncryptor {
    String encrypt(String rawPassword);
    boolean matches(String rawPassword, String encryptedPassword);
}
