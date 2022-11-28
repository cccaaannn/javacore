package com.kurtcan.javacore.security.aspects.abstracts;

import com.kurtcan.javacore.exception.UnauthorizedException;

import java.util.List;

public interface IAuthorizationProvider {
    List<String> getClaims() throws UnauthorizedException;
    void isAuthorized() throws UnauthorizedException;
}
