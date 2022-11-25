package com.kurtcan.javacore.utilities.ldap;

import javax.naming.AuthenticationException;
import javax.naming.AuthenticationNotSupportedException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

public class LdapUtils {

    public static void testConnection(String username, String password, String domainName, String port, String authenticationType) throws AuthenticationException, AuthenticationNotSupportedException, NamingException {
        Hashtable<String, String> environment = new Hashtable<String, String>();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        environment.put(Context.PROVIDER_URL, "ldap://" + domainName + ":" + port);
        environment.put(Context.SECURITY_AUTHENTICATION, authenticationType);
        environment.put(Context.SECURITY_PRINCIPAL, domainName + "\\" + username);
        environment.put(Context.SECURITY_CREDENTIALS, password);

        DirContext context = new InitialDirContext(environment);
        context.close();
    }

    public static LdapAuthenticationResult authenticate(String username, String password, String domainName, String port, String authenticationType) {
        try {
            LdapUtils.testConnection(
                    username,
                    password,
                    domainName,
                    port,
                    authenticationType
            );
        } catch (AuthenticationNotSupportedException exception) {
            return LdapAuthenticationResult.NOT_SUPPORTED;
        } catch (AuthenticationException exception) {
            return LdapAuthenticationResult.FAILED;
        } catch (NamingException exception) {
            return LdapAuthenticationResult.NAMING_EXCEPTION;
        }

        return LdapAuthenticationResult.AUTHENTICATED;
    }

}