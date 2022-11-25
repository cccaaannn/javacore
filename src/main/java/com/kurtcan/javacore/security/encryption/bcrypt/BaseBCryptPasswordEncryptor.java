package com.kurtcan.javacore.security.encryption.bcrypt;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class BaseBCryptPasswordEncryptor {

    public static String encrypt(String password, Integer logRounds) {
        return BCrypt.hashpw(password, BCrypt.gensalt(logRounds));
    }

    public static boolean matches(String rawPassword, String hash) {
        return BCrypt.checkpw(rawPassword, hash);
    }

}