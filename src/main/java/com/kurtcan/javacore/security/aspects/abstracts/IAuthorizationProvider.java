package com.kurtcan.javacore.security.aspects.abstracts;

import com.kurtcan.javacore.exception.UnAuthorizedException;

import java.util.List;

public interface IAuthorizationProvider {
    List<String> getClaims() throws UnAuthorizedException;
    void isAuthorized() throws UnAuthorizedException;
}
