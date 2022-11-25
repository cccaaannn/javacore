package com.kurtcan.javacore.utilities.ldap;

public enum LdapAuthenticationResult {
    FAILED(0),
    AUTHENTICATED(1),
    NOT_SUPPORTED(2),
    NAMING_EXCEPTION(3);

    private final int status;

    public int getStatus() {
        return status;
    }

    LdapAuthenticationResult(int status) {
        this.status = status;
    }
}