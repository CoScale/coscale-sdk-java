package com.coscale.sdk.client;

/**
 * Credentials object will be used to login on CoScale API. There are 2 ways to
 * authenticate on CoScale API:
 * 
 * 1. Authenticating with an access token;
 * 
 * 2. Authenticating with your email and password.
 * 
 * @author cristi
 *
 */
public class Credentials {

    /**
     * Token is used for Authenticating with an access token.
     * 
     * @param accessToken
     *            obtained from CoScale user account.
     * @return Credentials for authentication with an access token.
     */
    public static Credentials Token(String accessToken) {
        return new Token(accessToken);
    }

    /**
     * Authenticating with email and password.
     * 
     * @param email
     *            CoScale user email address.
     * @param password
     *            CoScale user password.
     * @return Credentials for authentication with user email and password.
     */
    public static Credentials Password(String email, String password) {
        return new Password(email, password);
    }

    /**
     * usesToken is used to determine which authentication method is used.
     * 
     * @return boolean true if Token authentication is used.
     */
    public boolean usesToken() {
        return this instanceof Token;
    }

    /**
     * Authenticating with an access token.
     */
    static class Token extends Credentials {
        public final String accessToken;

        public Token(String accessToken) {
            this.accessToken = accessToken;
        }
    }

    /**
     * Authenticating with email and password.
     */
    static class Password extends Credentials {
        public final String email;
        public final String password;

        public Password(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }

    /**
     * TokenHelper will be used to parse the login request response in order to
     * get the Authorization Token.
     */
    public static class TokenHelper {
        public String token;
    }
}
