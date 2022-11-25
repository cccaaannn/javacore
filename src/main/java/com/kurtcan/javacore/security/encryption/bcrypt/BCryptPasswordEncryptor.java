package com.kurtcan.javacore.security.encryption.bcrypt;

import com.kurtcan.javacore.security.encryption.abstracts.IPasswordEncryptor;

public class BCryptPasswordEncryptor implements IPasswordEncryptor {
    private final Integer logRounds;

    public BCryptPasswordEncryptor(Integer logRounds) {
        this.logRounds = logRounds;
    }

    @Override
    public String encrypt(String password) {
        return BaseBCryptPasswordEncryptor.encrypt(password, this.logRounds);
    }

    @Override
    public boolean matches(String rawPassword, String hash) {
        return BaseBCryptPasswordEncryptor.matches(rawPassword, hash);
    }

}