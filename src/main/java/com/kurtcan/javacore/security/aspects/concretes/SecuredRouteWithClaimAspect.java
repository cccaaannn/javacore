package com.kurtcan.javacore.security.aspects.concretes;

import com.kurtcan.javacore.exception.UnauthorizedException;
import com.kurtcan.javacore.security.aspects.abstracts.IAuthorizationProvider;
import com.kurtcan.javacore.security.aspects.annotations.SecuredRouteWithClaim;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

@Aspect
@Component
public class SecuredRouteWithClaimAspect {

    private final IAuthorizationProvider authorizationProvider;

    public SecuredRouteWithClaimAspect(IAuthorizationProvider authorizationProvider) {
        this.authorizationProvider = authorizationProvider;
    }

    @Around("@annotation(com.kurtcan.javacore.security.aspects.annotations.SecuredRouteWithClaim)")
    public Object initializeSecuredOperation(ProceedingJoinPoint joinPoint) throws Throwable {

        // Get the aspect from method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        SecuredRouteWithClaim securedRouteWithClaim = method.getAnnotation(SecuredRouteWithClaim.class);

        List<String> tokenClaims = authorizationProvider.getClaims();
        String[] methodClaims = securedRouteWithClaim.value();

        for (String tokenClaim : tokenClaims) {
            for (String methodClaim : methodClaims) {
                if (tokenClaim.equals(methodClaim)) {
                    return joinPoint.proceed();
                }
            }
        }

        throw new UnauthorizedException();
    }

}